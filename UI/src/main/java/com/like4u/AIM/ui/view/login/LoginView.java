package com.like4u.AIM.ui.view.login;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/26 22:04
 */
public class LoginView {
    private LoginInit loginInit;
    private ILoginEvent loginEvent;

    public LoginView(LoginInit loginInit, ILoginEvent loginEvent) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
    }
}
