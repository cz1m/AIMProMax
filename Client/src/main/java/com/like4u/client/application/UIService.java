package com.like4u.client.application;


import com.like4u.AIM.ui.view.chat.IChatMethod;
import com.like4u.AIM.ui.view.login.ILoginMethod;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
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
