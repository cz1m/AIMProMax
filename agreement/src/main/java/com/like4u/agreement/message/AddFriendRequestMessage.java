package com.like4u.agreement.message;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/9 21:08
 */
public class AddFriendRequestMessage extends Message{

    private String userId;//用户自己的Id
    private String friendId;   //好友Id

    public AddFriendRequestMessage(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
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

    @Override
    public int getMessageType() {
        return AddFriendRequest;
    }
}
