package com.like4u.client.socket.handler;

import com.like4u.AIM.ui.view.chat.IChatMethod;
import com.like4u.agreement.message.MsgResponseMessage;
import com.like4u.client.application.UIService;
import com.like4u.client.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 14:45
 * 接收好友消息消息处理
 */
public class MsgHandler extends MyBizHandler<MsgResponseMessage> {
    public MsgHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, MsgResponseMessage msg) {

        IChatMethod chat = uiService.getChat();
        Platform.runLater(() ->{
            chat.addTalkMsgUserLeft(msg.getFriendId(),msg.getMsgText(), msg.getMsgType().ordinal(),
                    msg.getMsgDate(),true,false,true);
        });
    }
}
