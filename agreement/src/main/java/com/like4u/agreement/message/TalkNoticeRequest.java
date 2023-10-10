package com.like4u.agreement.message;

import com.like4u.agreement.Enum.TalkTypeEnum;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/10 9:18
 */
public class TalkNoticeRequest extends Message{

    private String userId;
    private String FriendUserId;
    private TalkTypeEnum talkType;

    public TalkNoticeRequest(String userId, String friendUserId, Integer talkType) {
        this.userId = userId;
        FriendUserId = friendUserId;
        this.talkType = TalkTypeEnum.values()[talkType];
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendUserId() {
        return FriendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        FriendUserId = friendUserId;
    }

    public TalkTypeEnum getTalkType() {
        return talkType;
    }

    public void setTalkType(TalkTypeEnum talkType) {
        this.talkType = talkType;
    }

    @Override
    public int getMessageType() {
        return TalkNoticeRequest;
    }
}
