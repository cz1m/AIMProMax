package com.like4u.agreement.message;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message implements Serializable {

    public static Class<?> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    private int sequenceId;

    private int messageType;

    public abstract int getMessageType();

    public static final int LoginRequestMessage = 0;
    public static final int LoginResponseMessage = 1;
    public static final int ChatRequestMessage = 2;
    public static final int AddFriendRequest=3;
    public static final int AddFriendResponse=4;
    public static final int SearchFriendRequestMessage=5;
    public static final int SearchFriendResponseMessage=6;
    public static final int ReconnectRequest= 7;
    public static final int DelTalkRequest=8;
    public static final int TalkNoticeRequest=9;
    public static final int TalkNoticeResponse=10;

    private static final Map<Integer, Class<?>> messageClasses = new HashMap<>();

    static {
        messageClasses.put(LoginRequestMessage, LoginRequestMessage.class);
        messageClasses.put(LoginResponseMessage, LoginResponseMessage.class);
        messageClasses.put(ReconnectRequest,com.like4u.agreement.message.ReconnectRequest.class);
        messageClasses.put(AddFriendRequest, AddFriendRequestMessage.class);
        messageClasses.put(AddFriendResponse, AddFriendResponseMessage.class);
        messageClasses.put(SearchFriendRequestMessage,com.like4u.agreement.message.SearchFriendRequestMessage.class);
        messageClasses.put(SearchFriendResponseMessage,com.like4u.agreement.message.SearchFriendResponseMessage.class);
        messageClasses.put(DelTalkRequest, DelTalkRequestMessage.class);
        messageClasses.put(TalkNoticeRequest,com.like4u.agreement.message.TalkNoticeRequest.class);
        messageClasses.put(TalkNoticeResponse,com.like4u.agreement.message.TalkNoticeResponse.class);
    }
}
