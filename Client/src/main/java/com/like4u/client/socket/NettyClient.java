package com.like4u.client.socket;

import com.like4u.agreement.message.LoginRequestMessage;
import com.like4u.agreement.message.LoginResponseMessage;
import com.like4u.agreement.protocol.MessageCodecSharable;
import com.like4u.agreement.protocol.ProcotolFrameDecoder;
import com.like4u.client.application.UIService;
import com.like4u.client.infrastructure.util.BeanUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/3 17:56
 */

@Slf4j
public class NettyClient implements Callable<Channel> {
    private Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private String inetHost = "127.0.0.1";
    private int inetPort = 7397;

    private  Channel channel;
    public static String user = "";
    NioEventLoopGroup group = new NioEventLoopGroup();

    private UIService uiService;

    public NettyClient(UIService uiService) {
        this.uiService = uiService;
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


    @Override
    public Channel call() throws Exception {

        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        AtomicBoolean loginSignal = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1);
        ChannelFuture channelFuture=null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .channel(NioSocketChannel.class);
            b.handler(new MyChannelInitializer(uiService));
            channelFuture = b.connect(inetHost, inetPort).sync();
            this.channel=channelFuture.channel();
            BeanUtil.addBean("channel", channel);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                logger.info("socket client start done. ");
            } else {
                logger.error("socket client start error. ");
            }
        }
        return channel;
    }
}
