package com.like4u.client.socket;

import com.like4u.agreement.codec.ObjDecoder;
import com.like4u.agreement.codec.ObjEncoder;
import com.like4u.agreement.message.LoginRequestMessage;
import com.like4u.agreement.message.LoginResponseMessage;
import com.like4u.agreement.protocol.MessageCodecSharable;
import com.like4u.agreement.protocol.ProcotolFrameDecoder;
import com.like4u.client.application.UIService;
import com.like4u.client.socket.handler.AddFriendHandler;
import com.like4u.client.socket.handler.LoginHandler;
import com.like4u.client.socket.handler.SearchFriendHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/3 18:44
 */
@Slf4j
public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private UIService uiService;
    LoggingHandler LOGGING_HANDLER= new LoggingHandler(LogLevel.DEBUG);
    MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
    AtomicBoolean loginSignal = new AtomicBoolean(false);
    CountDownLatch latch = new CountDownLatch(1);

    public MyChannelInitializer(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void initChannel(NioSocketChannel channel) throws Exception {
        channel.pipeline().addLast(new ObjDecoder());
        // 在管道中添加我们自己的接收数据实现方法

        channel.pipeline().addLast(new LoginHandler(uiService));
        channel.pipeline().addLast(new SearchFriendHandler(uiService));
        channel.pipeline().addLast(new AddFriendHandler(uiService));
        channel.pipeline().addLast(new LoginHandler(uiService));

        //对象传输处理[编码]
        channel.pipeline().addLast(new ObjEncoder());
    }

   /* @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {

        ch.pipeline().addLast(new ProcotolFrameDecoder());
        ch.pipeline().addLast(LOGGING_HANDLER);

        ch.pipeline().addLast(MESSAGE_CODEC);
        ch.pipeline().addLast(new LoginHandler(uiService));
*//*                          ch.pipeline().addLast(new IdleStateHandler(0,3,0));
                            ch.pipeline().addLast(new ChannelDuplexHandler(){
                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    IdleStateEvent event  = (IdleStateEvent)evt;
                                    if (event.state()== IdleState.WRITER_IDLE) {

                                        ctx.writeAndFlush(new PingMessage());
                                    }
                                }
                            });*//*

        ch.pipeline().addLast("clientHandler", new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                if (msg instanceof LoginResponseMessage) {
                    LoginResponseMessage response = (LoginResponseMessage) msg;
                    if (response.isSuccess()) {
                        loginSignal.set(true);
                    }
                    latch.countDown();

                }

            }

        });
    }*/

}
