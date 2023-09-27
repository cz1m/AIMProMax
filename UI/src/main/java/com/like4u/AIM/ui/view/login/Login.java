package com.like4u.AIM.ui.view.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import javafx.scene.control.Button;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/26 20:43
 */
public class Login extends Stage {

    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";
    private Parent root;

    private double xOffset;
    private double yOffset;
    private void move() {
        root.setOnMousePressed(event -> {
            xOffset = getX() - event.getScreenX();
            yOffset = getY() - event.getScreenY();
            root.setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseDragged(event -> {
            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });
        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }

/*    private void min() {
        Button login_min = $("login_min", Button.class);
        login_min.setOnAction(event -> {
            System.out.println("最小化窗体");
            setIconified(true);
        });
    }
    private void quit() {
        Button login_close = $("login_close", Button.class);
        login_close.setOnAction(event -> {
            System.out.println("退出窗体");
            close();
            System.exit(0);
        });
    }*/



    public Login(){
        try{
            root= FXMLLoader.load(getClass().getResource((RESOURCE_NAME)));
        }catch (IOException e){
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/login/img/system/logo.png"));



    }
}
