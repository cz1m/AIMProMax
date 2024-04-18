package com.like4u.AIM.ui.view.login;

public class LoginEventDefine {
    private LoginInit loginInit;
    private ILoginEvent loginEvent;
    private ILoginMethod loginMethod;

    public LoginEventDefine(LoginInit loginInit, ILoginEvent loginEvent,
                            ILoginMethod loginMethod) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
        this.loginMethod = loginMethod;
        loginInit.move();
        min();
        quit();
        doEventLogin();
    }

    // 事件；最小化
    private void min() {
        loginInit.login_min.setOnAction(event -> {
            loginInit.setIconified(true);
        });

    }

    // 事件；退出
    private void quit() {
        loginInit.login_close.setOnAction(event -> {
            loginInit.close();
            System.exit(0);
        });
    }

    // 事件；登陆
    private void doEventLogin() {
        loginInit.login_button.setOnAction(event -> {
            // 假设 doLoginCheck 方法现在返回一个布尔值表示成功或失败
            System.out.println("客户端发送消息");
          loginEvent.doLoginCheck(loginInit.userId.getText(),
                    loginInit.userPassword.getText());
            /*if (!loginSuccess) {
                // 如果登录失败，显示登录失败的消息
                loginInit.loginFailMessage.setVisible(true);
            } else {
                // 如果登录成功，隐藏登录失败的消息（如果之前显示过）
                loginInit.loginFailMessage.setVisible(false);
                // 可以在这里处理登录成功后的逻辑
            }*/
        });
    }
}
