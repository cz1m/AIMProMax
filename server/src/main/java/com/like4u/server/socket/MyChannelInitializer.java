package com.like4u.server.socket;

import com.like4u.agreement.protocol.MessageCodecSharable;
import com.like4u.agreement.protocol.ProcotolFrameDecoder;
import com.like4u.server.application.UserService;
import com.like4u.server.socket.handler.LoginHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/8 22:03
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private UserService userService;

    public MyChannelInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ProcotolFrameDecoder());
        ch.pipeline().addLast(new MessageCodecSharable());
        ch.pipeline().addLast(new LoginHandler(userService));
    }
}
