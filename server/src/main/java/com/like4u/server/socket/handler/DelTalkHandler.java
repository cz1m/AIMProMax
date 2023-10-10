package com.like4u.server.socket.handler;

import com.like4u.agreement.message.DelTalkRequestMessage;
import com.like4u.server.application.UserService;
import com.like4u.server.socket.MyBizHandler;
import io.netty.channel.Channel;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 10:20
 */
public class DelTalkHandler extends MyBizHandler<DelTalkRequestMessage> {
    public DelTalkHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, DelTalkRequestMessage msg) {
        userService.deleteUserTalk(msg.getUserId(),msg.getTalkId());
    }
}
