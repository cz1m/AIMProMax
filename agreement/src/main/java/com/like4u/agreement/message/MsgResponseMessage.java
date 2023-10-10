package com.like4u.agreement.message;

import com.like4u.agreement.Enum.MsgTypeEnum;

import java.util.Date;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 10:47
 */
public class MsgResponseMessage extends Message{
    private String friendId;
    private String msgText;
    private Date msgDate;
    private MsgTypeEnum msgType;

    public MsgResponseMessage(String friendId, String msgText, Date msgDate) {
        this.friendId = friendId;
        this.msgText = msgText;
        this.msgDate = msgDate;
        this.msgType=MsgTypeEnum.Text;
    }

    public MsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
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
        return MsgResponseMessage;
    }
}
