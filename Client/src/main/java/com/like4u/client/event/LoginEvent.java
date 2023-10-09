package com.like4u.client.event;

import com.like4u.AIM.ui.view.login.ILoginEvent;
import com.like4u.agreement.message.LoginRequestMessage;
import com.like4u.client.infrastructure.util.BeanUtil;
import com.like4u.client.infrastructure.util.CacheUtil;
import io.netty.channel.Channel;


/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/3 21:34
 */
public class LoginEvent implements ILoginEvent {
    @Override
    public void doLoginCheck(String userId, String userPassword) {

        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new LoginRequestMessage(userId,userPassword));
        CacheUtil.userId=userId;
    }
}
