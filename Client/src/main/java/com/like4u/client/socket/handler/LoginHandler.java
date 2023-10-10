package com.like4u.client.socket.handler;

import com.alibaba.fastjson.JSON;
import com.like4u.AIM.ui.view.chat.IChatMethod;
import com.like4u.agreement.Enum.MsgTypeEnum;
import com.like4u.agreement.Enum.MsgUserType;
import com.like4u.agreement.message.LoginResponseMessage;
import com.like4u.agreement.protocol.dto.ChatRecordDto;
import com.like4u.agreement.protocol.dto.ChatTalkDto;
import com.like4u.agreement.protocol.dto.GroupsDto;
import com.like4u.agreement.protocol.dto.UserFriendDto;
import com.like4u.client.application.UIService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

import java.util.List;

import static com.like4u.agreement.Enum.MsgTypeEnum.*;
import static com.like4u.agreement.Enum.MsgUserType.*;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/8 10:55
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponseMessage> {

    private UIService uiService;

    public LoginHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponseMessage msg) throws Exception {
        System.out.println("\r\n> msg handler ing ...");
        System.out.println("消息内容：" + JSON.toJSONString(msg));
        if (!msg.isSuccess()) {
            System.out.println("登陆失败");
            return;
        }
        Platform.runLater(() -> {
            uiService.getLogin().doLoginSuccess();
            IChatMethod chat = uiService.getChat();
            // 设置用户信息
            chat.setUserInfo(msg.getUserId(), msg.getUserNickName(), msg.getUserHead());
            // 对话框
            List<ChatTalkDto> chatTalkList = msg.getChatTalkList();
            if (null != chatTalkList) {
                chatTalkList.forEach(talk -> {
                            chat.addTalkBox(0, talk.getTalkType().ordinal(), talk.getTalkId(),
                                    talk.getTalkName(), talk.getTalkHead(), talk.getTalkSketch(), talk.getTalkDate(), true);
                            switch (talk.getTalkType()) {
                                // 好友
                                case Friend -> {
                                    List<ChatRecordDto> userRecordList = talk.getChatRecordList();
                                    if (null == userRecordList || userRecordList.isEmpty()) return;
                                    for (int i = userRecordList.size() - 1; i >= 0; i--) {
                                        ChatRecordDto chatRecord = userRecordList.get(i);
                                        //  自己的消息
                                        if (My == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(),
                                                    chatRecord.getMsgType().ordinal(), chatRecord.getMsgDate(),
                                                    true, false, false);
                                            continue;
                                        }
                                        // 好友的消息
                                        if (Other == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgUserLeft(chatRecord.getTalkId(), chatRecord.getMsgContent(),
                                                    chatRecord.getMsgType().ordinal(), chatRecord.getMsgDate(),
                                                    true, false, false);
                                        }
                                    }
                                }
                                // 群组
                                case Group -> {
                                    List<ChatRecordDto> groupRecordList = talk.getChatRecordList();
                                    if (null == groupRecordList || groupRecordList.isEmpty()) return;
                                    for (int i = groupRecordList.size() - 1; i >= 0; i--) {
                                        ChatRecordDto chatRecord = groupRecordList.get(i);
                                        //  自己的消息
                                        if (My == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(),
                                                    chatRecord.getMsgType().ordinal(), chatRecord.getMsgDate(),
                                                    true, false, false);
                                            continue;
                                        }
                                        // 他人的消息
                                        if (Other == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgGroupLeft(chatRecord.getTalkId(), chatRecord.getUserId(),
                                                    chatRecord.getUserNickName(), chatRecord.getUserHead(), chatRecord.getMsgContent(),
                                                    chatRecord.getMsgType().ordinal(), chatRecord.getMsgDate(), true, false, false);
                                        }
                                    }
                                }
                                default -> {
                                }
                            }

                        }
                );

            }
            // 群组
            List<GroupsDto> groupsList = msg.getGroupsList();
            if (null != groupsList) {
                groupsList.forEach(groups -> chat.addFriendGroup(groups.getGroupId(), groups.getGroupName(), groups.getGroupHead()));
            }
            // 好友
            List<UserFriendDto> userFriendList = msg.getUserFriendList();
            if (null != userFriendList) {
                userFriendList.forEach(friend -> chat.addFriendUser(false, friend.getFriendId(), friend.getFriendName(), friend.getFriendHead()));
            }
        });
    }
}
