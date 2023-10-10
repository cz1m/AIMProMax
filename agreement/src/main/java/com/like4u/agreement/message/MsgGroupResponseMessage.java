package com.like4u.agreement.message;

import com.like4u.agreement.Enum.MsgTypeEnum;

import java.util.Date;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 15:11
 */
public class MsgGroupResponseMessage extends Message{
    private String talkId;
    private String userId;
    private String userNickName;
    private String userHead;
    private String msg;
    private Date msgDate;
    private MsgTypeEnum msgType;

    public MsgGroupResponseMessage() {
    }

    public MsgGroupResponseMessage(String talkId, String userId, String userNickName, String userHead, String msg, Date msgDate) {
        this.talkId = talkId;
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
        this.msg = msg;
        this.msgDate = msgDate;
        this.msgType=MsgTypeEnum.Text;
    }

    public MsgGroupResponseMessage(String talkId, String userId, String userNickName, String userHead, String msg, Date msgDate, MsgTypeEnum msgType) {
        this.talkId = talkId;
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
        this.msg = msg;
        this.msgDate = msgDate;
        this.msgType = msgType;
    }

    public MsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    @Override
    public int getMessageType() {
        return MsgGroupResponseMessage;
    }
}
