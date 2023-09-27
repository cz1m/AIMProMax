package com.like4u.AIM.ui.view.login;

import com.like4u.AIM.ui.UIObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import javafx.scene.control.TextField;

import java.io.IOException;
import javafx.scene.control.Button;
/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/26 21:56
 */
public abstract class LoginInit extends UIObject {
    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";

    protected ILoginEvent loginEvent;

    public Button login_min; // 登陆窗口最小化
    public Button login_close; // 登陆窗口退出
    public Button login_button; // 登陆按钮
    public TextField userId; // 用户账户窗口
    public PasswordField userPassword;// 用户密码窗口
    LoginInit(ILoginEvent loginEvent) {
        this.loginEvent = loginEvent;
        try {
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/login/img/system/logo.png"));
        obtain();
        initView();
        initEventDefine();
    }
    private void obtain() {
        login_min = $("login_min", Button.class);
        login_close = $("login_close", Button.class);
        login_button = $("login_button", Button.class);
        userId = $("userId", TextField.class);
        userPassword = $("userPassword", PasswordField.class);
    }

}
