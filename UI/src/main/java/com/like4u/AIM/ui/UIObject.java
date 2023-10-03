package com.like4u.AIM.ui;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *  底层UI相关调用：使用Stage的API获取窗口大小和位置，获取画笔等
 */
public abstract class UIObject extends Stage {
    protected Parent root;
    private double xOffset;
    private double yOffset;

    public <T> T $(String id, Class<T> clazz) {
        return (T) root.lookup("#" + id);
    }

    /**
     * 清除列表中的选中标识
     * @param listViews 列表
     */
    public void clearViewListSelectedAll(ListView<Pane>... listViews) {
        for (ListView<Pane> listView : listViews) {
            listView.getSelectionModel().clearSelection();
        }
    }

    public void move() {
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
    public double x(){
        return getX();
    }

    public double y(){
        return getY();
    }
    public double width(){
        return getWidth();
    }

    public double height(){
        return getHeight();
    }

    // 初始化页面
    public abstract void initView();

    // 初始化事件定义
    public abstract void initEventDefine();}