package com.like4u.AIM.ui.view.chat.element.group_bar_chat;

import com.like4u.AIM.ui.util.DateUtil;
import com.like4u.AIM.ui.util.Ids;
import com.like4u.AIM.ui.view.chat.data.RemindCount;
import com.like4u.AIM.ui.view.chat.data.TalkBoxData;
import com.like4u.AIM.ui.view.chat.data.TalkData;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;


import java.util.Date;

/**
 * 对话框元素，好友对话列表框元素
 */
public class ElementTalk {

    private Pane pane;       // 对话面板(与好友对话、与群组对话)

    private Label head;      // 头像区域
    private Label nikeName;  // 昵称区域
    private Label msgSketch; // 信息简述
    private Label msgData;   // 信息时间
    private Label msgRemind; // 消息提醒
    private Button delete;   // 删除对话框按钮

    private ListView<Pane>  infoBoxList; // 初始化填充消息对话框

    /**
     * 初始化构造对话框
     * @param talkId 对话框id
     * @param talkType 对话类型：好友0，群组1
     * @param talkName 对话框名称
     * @param talkHead 对话框头像
     * @param talkSketch 对话框简述
     * @param talkDate 对话时间
     */

    public ElementTalk(String talkId, Integer talkType, String talkName, String talkHead, String talkSketch, Date talkDate) {
        pane = new Pane();
        pane.setId(Ids.ElementTalkId.createTalkPaneId(talkId));
        pane.setUserData(new TalkBoxData(talkId, talkType, talkName, talkHead));//为对话框设置用户数据
        pane.setPrefSize(270, 80);
        pane.getStyleClass().add("talkElement");
        ObservableList<Node> children = pane.getChildren();

        // 头像区域
        head = new Label();
        head.setPrefSize(50, 50);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("element_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", talkHead));
        children.add(head);

        // 昵称区域
        nikeName = new Label();
        nikeName.setPrefSize(140, 25);
        nikeName.setLayoutX(80);
        nikeName.setLayoutY(15);
        nikeName.setText(talkName);
        nikeName.getStyleClass().add("element_nikeName");
        children.add(nikeName);

        // 信息简述
        msgSketch = new Label();
        msgSketch.setId(Ids.ElementTalkId.createMsgSketchId(talkId));
        msgSketch.setPrefSize(200, 25);
        msgSketch.setLayoutX(80);
        msgSketch.setLayoutY(40);
        msgSketch.getStyleClass().add("element_msgSketch");
        children.add(msgSketch);

        // 信息时间
        msgData = new Label();
        msgData.setId(Ids.ElementTalkId.createMsgDataId(talkId));
        msgData.setPrefSize(60, 25);
        msgData.setLayoutX(220);
        msgData.setLayoutY(15);
        msgData.getStyleClass().add("element_msgData");
        children.add(msgData);
        // 填充；信息简述 & 信息时间
        fillMsgSketch(talkSketch, talkDate);

        // 消息提醒
        msgRemind = new Label();
        msgRemind.setPrefSize(15, 15);
        msgRemind.setLayoutX(60);
        msgRemind.setLayoutY(5);
        msgRemind.setUserData(new RemindCount());
        msgRemind.setText("");
        msgRemind.setVisible(false);
        msgRemind.getStyleClass().add("element_msgRemind");
        children.add(msgRemind);

        // 删除对话框按钮
        delete = new Button();
        delete.setVisible(false);
        delete.setPrefSize(4, 4);
        delete.setLayoutY(26);
        delete.setLayoutX(-8);
        delete.getStyleClass().add("element_delete");
        children.add(delete);

        // 消息框[初始化，未装载]，承载对话信息内容，点击按钮时候填充
        infoBoxList = new ListView<>();
        infoBoxList.setId(Ids.ElementTalkId.createInfoBoxListId(talkId));
        infoBoxList.setUserData(new TalkData(talkName, talkHead));
        infoBoxList.setPrefSize(850, 560);
        infoBoxList.getStyleClass().add("infoBoxStyle");

    }
    public ListView<Pane> infoBoxList() {
        return infoBoxList;
    }

    public Pane pane() {
        return pane;
    }

    public Button delete() {
        return delete;
    }

    /**
     * 推送消息到对话框
     * @param talkSketch 消息内容切片
     * @param talkDate  推送时间
     */

    public void fillMsgSketch(String talkSketch, Date talkDate) {
        if (null != talkSketch) {
            if (talkSketch.length() > 30) talkSketch = talkSketch.substring(0, 30);
            msgSketch.setText(talkSketch);
        }
        if (null == talkDate) talkDate = new Date();
        // 格式化信息
        String talkSimpleDate = DateUtil.simpleDate(talkDate);
        msgData.setText(talkSimpleDate);
    }

    public void clearMsgSketch() {
        msgSketch.setText("");
    }

    /**
     * 消息提醒
     * @return 返回一个小红点标签
     */
    public Label msgRemind() {
        return msgRemind;
    }
}
