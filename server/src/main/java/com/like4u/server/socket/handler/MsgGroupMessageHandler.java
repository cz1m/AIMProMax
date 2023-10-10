package com.like4u.server.socket.handler;

import com.like4u.agreement.Enum.MsgTypeEnum;
import com.like4u.agreement.Enum.TalkTypeEnum;
import com.like4u.agreement.message.MsgGroupRequestMessage;
import com.like4u.agreement.message.MsgGroupResponseMessage;
import com.like4u.server.application.UserService;
import com.like4u.server.domain.user.model.ChatRecordInfo;
import com.like4u.server.domain.user.model.UserInfo;
import com.like4u.server.infrastructrue.comon.SocketChannelUtil;
import com.like4u.server.socket.MyBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 15:35
 */
public class MsgGroupMessageHandler extends MyBizHandler<MsgGroupRequestMessage> {
    public MsgGroupMessageHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, MsgGroupRequestMessage msg) {

        ChannelGroup channelGroup = SocketChannelUtil.getChannelGroup(msg.getTalkId());
        if (channelGroup==null){
            SocketChannelUtil.addChannelGroup(msg.getTalkId(),channel);
            channelGroup=SocketChannelUtil.getChannelGroup(msg.getTalkId());
        }
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(),msg.getTalkId(),msg.getMsgText()
                , MsgTypeEnum.Text,msg.getMsgDate(),TalkTypeEnum.Group.ordinal()));

        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
        MsgGroupResponseMessage msgGroupResponse = new MsgGroupResponseMessage(
                msg.getTalkId(), msg.getUserId(), userInfo.getUserNickName(),
                userInfo.getUserHead(), msg.getMsgText(), msg.getMsgDate(),msg.getMsgType());

        channelGroup.writeAndFlush(msgGroupResponse);


    }
}
