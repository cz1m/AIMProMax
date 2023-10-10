package com.like4u.server.socket.handler;

import com.alibaba.fastjson.JSON;
import com.like4u.agreement.Enum.TalkTypeEnum;
import com.like4u.agreement.message.MsgRequestMessage;
import com.like4u.agreement.message.MsgResponseMessage;
import com.like4u.server.application.UserService;
import com.like4u.server.domain.user.model.ChatRecordInfo;
import com.like4u.server.infrastructrue.comon.SocketChannelUtil;
import com.like4u.server.socket.MyBizHandler;
import io.netty.channel.Channel;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 11:06
 * 收发消息
 */
public class MsgHandler extends MyBizHandler<MsgRequestMessage> {
    public MsgHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, MsgRequestMessage msg) {
        logger.info("消息信息处理：{}", JSON.toJSONString(msg));
        //异步消息入库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(),msg.getFriendId(),msg.getMsgTest(),msg.getMsgType(),msg.getMsgDate()));
        //添加对话框(如果不存在则添加)
        userService.addTalkBoxInfo(msg.getFriendId(),msg.getUserId(), TalkTypeEnum.Friend.ordinal());
        //获取好友通讯
        Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendId());
        if (friendChannel==null){
            logger.info("用户id ：{}未登录", msg.getFriendId());
            return;
        }
        friendChannel.writeAndFlush(new MsgResponseMessage(msg.getUserId(),msg.getMsgTest(),msg.getMsgDate()));


    }
}
