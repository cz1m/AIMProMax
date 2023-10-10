package com.like4u.agreement.message;

import java.util.Date;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 9:17
 *  对话应答消息
 */
public class DelTalkRequestMessage extends Message{

    private String userId;
    private String talkId;


    public DelTalkRequestMessage(String userId, String talkId) {
        this.userId = userId;
        this.talkId = talkId;
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

    @Override
    public int getMessageType() {
        return DelTalkRequest;
    }
}
