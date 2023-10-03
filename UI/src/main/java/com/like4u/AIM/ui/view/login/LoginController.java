package com.like4u.AIM.ui.view.login;

import com.like4u.AIM.ui.view.chat.IChatMethod;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/26 21:55
 */
public class LoginController extends LoginInit implements ILoginMethod{
    private IChatMethod chat;
    private LoginView loginView;
    private LoginEventDefine loginEventDefine;
    public LoginController(ILoginEvent loginEvent, IChatMethod chat) {
        super(loginEvent);
        this.chat = chat;
    }
    @Override
    public void initView() {
        loginView = new LoginView(this, loginEvent);
    }
    @Override
    public void initEventDefine() {
        loginEventDefine = new LoginEventDefine(this, loginEvent, this);
    }
    @Override
    public void doShow() {
        super.show();
    }
    @Override
    public void doLoginError() {
        System.out.println("登陆失败，执行提示操作");
    }
    @Override
    public void doLoginSuccess() {
        // 关闭原窗口
        close();
        // 打开聊天窗口
        chat.doShow();
    }

}
