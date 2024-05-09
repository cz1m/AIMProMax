package com.like4u.AIM.ui.view.chat.element.group_bar_friend;

public class GroupMember {

    private String friendId;    // 好友ID
    private String friendName;  // 好友名称
    private String friendHead;  // 好友头像

    public GroupMember() {
    }

    public GroupMember(String friendId, String friendName, String friendHead) {
        this.friendId = friendId;
        this.friendName = friendName;
        this.friendHead = friendHead;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendHead() {
        return friendHead;
    }

    public void setFriendHead(String friendHead) {
        this.friendHead = friendHead;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "friendId='" + friendId + '\'' +
                ", friendName='" + friendName + '\'' +
                ", friendHead='" + friendHead + '\'' +
                '}';
    }
}
