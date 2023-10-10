package com.like4u.agreement.message;

import com.like4u.agreement.Enum.MsgTypeEnum;

import java.util.Date;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 15:11
 */
public class MsgGroupRequestMessage extends Message{

    private String userId;
    private String talkId;
    private String msgText;
    private Date msgDate;
    private MsgTypeEnum msgType;

    public MsgGroupRequestMessage(String userId, String talkId, String msgText, Date msgDate) {
        this.userId = userId;
        this.talkId = talkId;
        this.msgText = msgText;
        this.msgDate = msgDate;
        this.msgType=MsgTypeEnum.Text;
    }

    public MsgGroupRequestMessage(String userId, String talkId, String msgText, Date msgDate, MsgTypeEnum msgType) {
        this.userId = userId;
        this.talkId = talkId;
        this.msgText = msgText;
        this.msgDate = msgDate;
        this.msgType = msgType;
    }

    public MsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    @Override
    public int getMessageType() {
        return MsgGroupRequestMessage;
    }
}
