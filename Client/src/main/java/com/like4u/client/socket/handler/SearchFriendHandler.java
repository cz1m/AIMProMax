package com.like4u.client.socket.handler;

import com.like4u.AIM.ui.view.chat.IChatMethod;
import com.like4u.agreement.message.SearchFriendResponseMessage;
import com.like4u.agreement.protocol.dto.UserDto;
import com.like4u.client.application.UIService;
import com.like4u.client.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/9 22:04
 * 搜索好友的处理.收到服务器发来的好友列表，将好友信息填充到窗体上。
 */
public class SearchFriendHandler extends MyBizHandler<SearchFriendResponseMessage> {

    public SearchFriendHandler(UIService uiService) {
        super(uiService);
    }


    @Override
    public void channelRead(Channel channel, SearchFriendResponseMessage msg) {
        List<UserDto> list = msg.getList();
        if (list==null||list.isEmpty()) return;
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            for(UserDto user:list){
                chat.addLuckFriend(user.getUserId(), user.getUserNickName(), user.getUserHead(), user.getStatus());
            }
        });

    }
}
