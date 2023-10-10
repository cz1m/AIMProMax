package com.like4u.server.socket.handler;

import com.like4u.agreement.Enum.TalkTypeEnum;
import com.like4u.agreement.message.TalkNoticeRequest;
import com.like4u.agreement.message.TalkNoticeResponse;
import com.like4u.server.application.UserService;
import com.like4u.server.domain.user.model.UserInfo;
import com.like4u.server.infrastructrue.comon.SocketChannelUtil;
import com.like4u.server.socket.MyBizHandler;
import io.netty.channel.Channel;

import java.util.Date;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 9:53
 * 对话请求处理
 */
public class TalkNoticeHandler extends MyBizHandler<TalkNoticeRequest> {

    public TalkNoticeHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, TalkNoticeRequest msg) {

        switch (msg.getTalkType()){

            case Friend:
                //1. 对话框落库
                userService.addTalkBoxInfo(msg.getUserId(),msg.getFriendUserId(),TalkTypeEnum.Friend.ordinal());
                userService.addTalkBoxInfo(msg.getFriendUserId(),msg.getUserId(),TalkTypeEnum.Friend.ordinal());
                //2. 查询对话框信息
                UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
                //3. 发送对话框消息给好友
                TalkNoticeResponse response = new TalkNoticeResponse();
                response.setTalkId(userInfo.getUserId());
                response.setTalkName(userInfo.getUserNickName());
                response.setTalkHead(userInfo.getUserHead());
                response.setTalkSketch(null);
                response.setTalkDate(new Date());

                Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendUserId());
                if (friendChannel==null){
                    logger.info("用户id：{}，未登录！",msg.getFriendUserId());
                    return;
                }
                friendChannel.writeAndFlush(response);
                break;
            case Group:
                userService.addTalkBoxInfo(msg.getUserId(), msg.getFriendUserId(),TalkTypeEnum.Group.ordinal());
                break;
            default:
                logger.error("错误的消息类型？");
                break;

        }
    }
}
