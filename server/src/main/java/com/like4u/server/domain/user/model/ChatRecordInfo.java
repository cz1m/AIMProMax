package com.like4u.server.domain.user.model;



import com.like4u.agreement.Enum.MsgTypeEnum;
import com.like4u.agreement.Enum.TalkTypeEnum;
import com.like4u.server.infrastructrue.comon.Constants;

import java.util.Date;


public class ChatRecordInfo {

    private String userId;     // 用户ID
    private String friendId;   // 好友ID
    private String msgContent; // 消息内容
    private MsgTypeEnum msgType;   // 消息类型；0文字消息、1固定表情
    private Date msgDate;      // 消息时间
    private Integer talkType;  // 对话框类型；0好友、1群组

    public ChatRecordInfo() {
    }

    public ChatRecordInfo(String userId, String friendId, String msgContent, MsgTypeEnum msgType, Date msgDate) {
        this.friendId = friendId;
        this.userId = userId;
        this.msgContent = msgContent;
        this.msgType = msgType;
        this.msgDate = msgDate;
        this.talkType = TalkTypeEnum.Friend.ordinal();
    }

    public ChatRecordInfo(String userId, String friendId, String msgContent, MsgTypeEnum msgType, Date msgDate, Integer talkType) {
        this.friendId = friendId;
        this.userId = userId;
        this.msgContent = msgContent;
        this.msgType = msgType;
        this.msgDate = msgDate;
        this.talkType = talkType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public MsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
    }
}
