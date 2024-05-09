package com.like4u.agreement.message;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/5/5 17:23
 */
public class GroupMemberRequest extends Message{
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }



    public GroupMemberRequest(String groupId) {
        this.groupId = groupId;

    }
    @Override
    public int getMessageType() {
        return GroupMemberRequest;
    }
}
