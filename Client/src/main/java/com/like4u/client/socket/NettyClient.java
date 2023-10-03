package com.like4u.client.socket;

import com.like4u.agreement.message.LoginRequestMessage;
import com.like4u.agreement.message.LoginResponseMessage;
import com.like4u.agreement.protocol.MessageCodecSharable;
import com.like4u.agreement.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/3 17:56
 */
@Slf4j
public class NettyClient {

    private  Channel channel;
    public static String user = "";
    NioEventLoopGroup group = new NioEventLoopGroup();
    public  void main(String[] args) {


        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        AtomicBoolean loginSignal = new AtomicBoolean(false);


        CountDownLatch latch = new CountDownLatch(1);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .channel(NioSocketChannel.class);
            channel =   b.handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProcotolFrameDecoder());
                            ch.pipeline().addLast(LOGGING_HANDLER);

                            ch.pipeline().addLast(MESSAGE_CODEC);
/*                          ch.pipeline().addLast(new IdleStateHandler(0,3,0));
                            ch.pipeline().addLast(new ChannelDuplexHandler(){
                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    IdleStateEvent event  = (IdleStateEvent)evt;
                                    if (event.state()== IdleState.WRITER_IDLE) {

                                        ctx.writeAndFlush(new PingMessage());
                                    }
                                }
                            });*/

                            ch.pipeline().addLast("clientHandler", new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    log.debug("{}", msg);


                                    if (msg instanceof LoginResponseMessage) {
                                        LoginResponseMessage response = (LoginResponseMessage) msg;
                                        if (response.isSuccess()) {
                                            loginSignal.set(true);
                                        }
                                        latch.countDown();

                                    }

                                }

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {

                                    new Thread(() -> {
                                        Scanner scanner = new Scanner(System.in);
                                        System.out.println("请输入用户名");

                                        String username = scanner.nextLine();
                                        user = username;
                                        System.out.println("请输入密码");
                                        String password = scanner.nextLine();
                                        //Todo:账户密码校验

                                        LoginRequestMessage loginRequestMessage = new LoginRequestMessage(username, password);
                                        ctx.writeAndFlush(loginRequestMessage);

                                        System.out.println("等待后序输入");

                                        try {
                                            latch.await();
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }

                                        if (!loginSignal.get()) {
                                            ctx.channel().close();
                                            return;
                                        }
                                        do {
                                            //输入指令

                                            String command = scanner.nextLine();


                                        } while (false);

                                    }, "sysIn").start();


                                }
                            });
                        }
                    })
                    .connect(new InetSocketAddress("localhost", 8080))
                    .sync()
                    .channel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroyConnection(){
        if (channel==null){
            return;
        }
        channel.close();
        group.shutdownGracefully();
        channel.closeFuture().addListener((future)->{
            System.out.println("连接关闭成功");
        });


    }
    public boolean isActive(){
        return channel.isActive();
    }
    public Channel getChannel(){
        return channel;
    }


}
