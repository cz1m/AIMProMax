package com.like4u.server.socket.handler;

import com.like4u.agreement.message.ReconnectRequest;
import com.like4u.server.application.UserService;
import com.like4u.server.infrastructrue.comon.SocketChannelUtil;
import com.like4u.server.socket.MyBizHandler;
import io.netty.channel.Channel;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 19:45
 */
public class ReconnectHandler extends MyBizHandler<ReconnectRequest> {
    public ReconnectHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, ReconnectRequest msg) {
        logger.info("客户端断线重连处理：userId:{}", msg.getUserId());
        //添加用户channel
        SocketChannelUtil.removeUserChannelByUserId(msg.getUserId());
        SocketChannelUtil.addChannel(msg.getUserId(), channel);

        //添加群组Channel。实际就是吧自己的channel和所有有自己的群绑定
        List<String> groupsIdList=userService.queryTalkBoxGroupsIdList(msg.getUserId());
        for (String groupId : groupsIdList){
            SocketChannelUtil.addChannelGroup(groupId, channel);
        }


    }
}
