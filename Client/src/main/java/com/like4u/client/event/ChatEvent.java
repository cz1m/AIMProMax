package com.like4u.client.event;

import com.like4u.AIM.ui.view.chat.IChatEvent;
import com.like4u.agreement.Enum.MsgTypeEnum;
import com.like4u.agreement.Enum.TalkTypeEnum;
import com.like4u.agreement.message.*;
import com.like4u.client.infrastructure.util.BeanUtil;
import io.netty.channel.Channel;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;


import java.util.Date;

import static com.like4u.agreement.Enum.TalkTypeEnum.*;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/3 21:35
 */
public class ChatEvent implements IChatEvent {
    @Override
    public void doQuit() {

    }

    //发送消息
    @Override
    public void doSendMsg(String userId, String talkId, Integer talkType, String msg, Integer msgType, Date msgDate) {

        Channel channel = BeanUtil.getBean("channel", Channel.class);

        TalkTypeEnum talkTypeEnum = values()[talkType];
        switch (talkTypeEnum) {
            case Friend ->
                    channel.writeAndFlush(new MsgRequestMessage(userId, talkId, msg, msgDate, MsgTypeEnum.values()[msgType]));
            case Group ->
                    channel.writeAndFlush(new MsgGroupRequestMessage(userId, talkId, msg, msgDate, MsgTypeEnum.values()[msgType]));
            default -> {
                System.out.println("发生什么事了？消息无法发送");
            }
        }

    }

    /**
     * 添加用户对话框
     * @param userId       用户ID
     * @param userFriendId 好友ID
     */
    @Override
    public void doEventAddTalkUser(String userId, String userFriendId) {

        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new TalkNoticeRequest(userId,userFriendId, Friend.ordinal()));
    }

    @Override
    public void doEventAddTalkGroup(String userId, String groupId) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new TalkNoticeRequest(userId,groupId, Group.ordinal()));
    }

    @Override
    public void doEventDelTalkUser(String userId, String talkId) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new DelTalkRequestMessage(userId,talkId));
    }

    /**
     * 打开新的朋友窗体
     * @param userId   用户ID
     * @param listView 用户列表[非必需使用，同步接口可使用]
     */
    @Override
    public void addFriendLuck(String userId, ListView<Pane> listView) {

        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new SearchFriendRequestMessage(userId,""));//查询全部可添加好友

    }

    @Override
    public void doFriendLuckSearch(String userId, String text) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new SearchFriendRequestMessage(userId,text));//查询全部可添加好友
        System.out.println("------------------触发搜索好友事件");
    }

    @Override
    public void doEventAddLuckUser(String userId, String friendId) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new AddFriendRequestMessage(userId,friendId));
    }
}
