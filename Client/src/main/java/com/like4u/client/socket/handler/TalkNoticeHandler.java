package com.like4u.client.socket.handler;

import com.like4u.AIM.ui.view.chat.IChatMethod;
import com.like4u.agreement.message.TalkNoticeResponse;
import com.like4u.client.application.UIService;
import com.like4u.client.socket.MyBizHandler;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 10:24
 */
public class TalkNoticeHandler extends MyBizHandler<TalkNoticeResponse> {

    public TalkNoticeHandler(UIService uiService) {
        super(uiService);
    }


    @Override
    public void channelRead(Channel channel, TalkNoticeResponse msg) {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(()->{
            chat.addTalkBox(-1,msg.getMessageType(),msg.getTalkId(),msg.getTalkName(),
                    msg.getTalkHead(),msg.getTalkSketch(),msg.getTalkDate(),false);
        });
    }
}
