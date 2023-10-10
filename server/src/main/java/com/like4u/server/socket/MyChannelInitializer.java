package com.like4u.server.socket;

import com.like4u.agreement.codec.ObjDecoder;
import com.like4u.agreement.codec.ObjEncoder;
import com.like4u.server.application.UserService;
import com.like4u.server.socket.handler.*;
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

        //对象传输处理[解码]
        ch.pipeline().addLast(new ObjDecoder());
        ch.pipeline().addLast(new LoginHandler(userService));
        ch.pipeline().addLast(new AddFriendHandler(userService));
        ch.pipeline().addLast(new SearchFriendHandler(userService));
        ch.pipeline().addLast(new DelTalkHandler(userService));
        ch.pipeline().addLast(new TalkNoticeHandler(userService));
        ch.pipeline().addLast(new MsgHandler(userService));
        ch.pipeline().addLast(new MsgGroupMessageHandler(userService));

        ch.pipeline().addLast(new ObjEncoder());
    }
}
