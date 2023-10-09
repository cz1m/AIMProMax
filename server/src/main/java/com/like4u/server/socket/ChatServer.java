package com.like4u.server.socket;


import com.like4u.server.application.UserService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/15 19:36
 *
 */
@Slf4j
@Service("ChatServer")
public class ChatServer implements Callable<Channel> {
    private Logger logger= LoggerFactory.getLogger(ChatServer.class);
    private NioEventLoopGroup connectGroup = new NioEventLoopGroup(1);
    private NioEventLoopGroup work = new NioEventLoopGroup();


    private Channel channel;

    @Resource
    private UserService userService;

    public ChatServer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Channel call() {

        //MessageCodec messageCodec = new MessageCodec();

        ChannelFuture channelFuture=null;


        try {
             channelFuture= new ServerBootstrap()
                    .group(connectGroup, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                     .childHandler(new MyChannelInitializer(userService))
                    .bind(new InetSocketAddress(7397)).sync();

            Channel channel = channelFuture.channel();
            this.channel=channel;


        } catch (InterruptedException e) {
            logger.error("客户端出现致命异常,{}",e.getMessage());
        }finally {
            if (channelFuture!=null&&channelFuture.isSuccess()){
                logger.info("成功启动socket");
            }else {
                logger.error("socket炸掉了，快去看看你socket Server发生肾么事了");
            }

        }
        return channel;
    }
    public void destroy(){

        if (channel!=null){
            channel.close();
            work.shutdownGracefully();
            connectGroup.shutdownGracefully();
        }
    }
    public Channel getChannel(){
        return channel;
    }


}
