package com.like4u.agreement.message;

import com.like4u.agreement.Enum.MsgTypeEnum;

import java.util.Date;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 10:47
 */
public class MsgRequestMessage extends Message{

    private String userId;
    private String friendId;
    private String msgTest;
    private Date msgDate;
    private MsgTypeEnum msgType;

    public MsgRequestMessage(String userId, String friendId, String msgTest, Date msgDate, MsgTypeEnum msgType) {
        this.userId = userId;
        this.friendId = friendId;
        this.msgTest = msgTest;
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

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getMsgTest() {
        return msgTest;
    }

    public void setMsgTest(String msgTest) {
        this.msgTest = msgTest;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    @Override
    public int getMessageType() {
        return MsgRequestMessage;
    }
}
