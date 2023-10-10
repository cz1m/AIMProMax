package com.like4u.agreement.message;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/9 21:10
 */
public class AddFriendResponseMessage extends Message{

    private String friendId;
    private String friendNickName;
    private String friendHead;

    public AddFriendResponseMessage(String friendId, String friendNickName, String friendHead) {
        this.friendId = friendId;
        this.friendNickName = friendNickName;
        this.friendHead = friendHead;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
    }

    public String getFriendHead() {
        return friendHead;
    }

    public void setFriendHead(String friendHead) {
        this.friendHead = friendHead;
    }

    @Override
    public int getMessageType() {
        return AddFriendResponse;
    }
}
