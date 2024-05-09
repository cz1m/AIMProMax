package com.like4u.agreement.message;

import com.like4u.agreement.protocol.dto.UserFriendDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/5/5 17:31
 */
public class GroupMemberResponse extends Message {
    private boolean success;
    private List<UserFriendDto> userFriendList = new ArrayList<>();

    public GroupMemberResponse(boolean success, List<UserFriendDto> userFriendList) {
        this.success = success;
        this.userFriendList = userFriendList;
    }

    public GroupMemberResponse(List<UserFriendDto> userFriendList) {
        this.success= true;
        this.userFriendList = userFriendList;
    }

    public GroupMemberResponse(boolean success) {
        this.success = success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setUserFriendList(List<UserFriendDto> userFriendList) {
        this.userFriendList = userFriendList;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<UserFriendDto> getUserFriendList() {
        return userFriendList;
    }

    @Override
    public int getMessageType() {
        return GroupMemberResponse;
    }
}
