package com.like4u.agreement.message;

/**
 * 断线重连心跳包
 * 未来的坑： 设备信息、重连时间、网络环境、断线时消息记录
 */

public class ReconnectRequest extends Message {

    private String userId;

    public ReconnectRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public int getMessageType() {
        return ReconnectRequest;
    }
}
