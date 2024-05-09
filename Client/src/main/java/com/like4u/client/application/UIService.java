package com.like4u.client.application;


import com.like4u.AIM.ui.view.chat.IChatMethod;
import com.like4u.AIM.ui.view.login.ILoginMethod;

/**
 * 网络服务
 */
public class UIService {

    private ILoginMethod login;
    private IChatMethod chat;

    public ILoginMethod getLogin() {
        return login;
    }

    public void setLogin(ILoginMethod login) {
        this.login = login;
    }

    public IChatMethod getChat() {
        return chat;
    }

    public void setChat(IChatMethod chat) {
        this.chat = chat;
    }
}
