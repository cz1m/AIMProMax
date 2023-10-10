package com.like4u.client.socket.handler;

import com.like4u.AIM.ui.view.chat.IChatMethod;
import com.like4u.agreement.message.AddFriendResponseMessage;
import com.like4u.client.application.UIService;
import com.like4u.client.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/9 22:17
 */
public class AddFriendHandler extends MyBizHandler<AddFriendResponseMessage> {
    public AddFriendHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendResponseMessage msg) {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(()->{
            chat.addFriendUser(true, msg.getFriendId() ,msg.getFriendNickName(),msg.getFriendHead());
        });
    }
}
