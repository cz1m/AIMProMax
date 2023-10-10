package com.like4u.client.socket.handler;

import com.like4u.AIM.ui.view.chat.IChatMethod;
import com.like4u.agreement.message.MsgGroupResponseMessage;
import com.like4u.client.application.UIService;
import com.like4u.client.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 18:47
 */
public class MsgGroupHandler extends MyBizHandler<MsgGroupResponseMessage> {

    public MsgGroupHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, MsgGroupResponseMessage msg) {

        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addTalkMsgGroupLeft(msg.getTalkId(), msg.getUserId(), msg.getUserNickName(),
                    msg.getUserHead(), msg.getMsg(), msg.getMsgType().ordinal() ,msg.getMsgDate(),
                    true,false,true);
        });
    }
}
