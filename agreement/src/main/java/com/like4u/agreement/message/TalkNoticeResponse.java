package com.like4u.agreement.message;

import java.util.Date;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 9:18
 * 删除对话框请求消息
 */
public class TalkNoticeResponse extends Message{

    private String talkId;
    private String talkName;
    private String talkHead;
    private String talkSketch;
    private Date talkDate;


    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public String getTalkName() {
        return talkName;
    }

    public void setTalkName(String talkName) {
        this.talkName = talkName;
    }

    public String getTalkHead() {
        return talkHead;
    }

    public void setTalkHead(String talkHead) {
        this.talkHead = talkHead;
    }

    public String getTalkSketch() {
        return talkSketch;
    }

    public void setTalkSketch(String talkSketch) {
        this.talkSketch = talkSketch;
    }

    public Date getTalkDate() {
        return talkDate;
    }

    public void setTalkDate(Date talkDate) {
        this.talkDate = talkDate;
    }

    @Override
    public int getMessageType() {
        return TalkNoticeResponse;
    }
}
