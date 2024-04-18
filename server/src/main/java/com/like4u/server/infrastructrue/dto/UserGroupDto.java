package com.like4u.server.infrastructrue.dto;

import com.like4u.server.infrastructrue.po.Groups;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/11 9:54
 */
public class UserGroupDto {
    private String userId;
    private String userNickName;
    private List<Groups> groups;

    public UserGroupDto(String userid, String userNickname, List<com.like4u.server.infrastructrue.po.Groups> groups) {
        this.userId = userid;
        this.userNickName = userNickname;
        this.groups = groups;
    }
    public UserGroupDto(String userId, String userNickName, String groupId, String groupName, String groupHead) {
        this.userId = userId;
        this.userNickName = userNickName;
        // 初始化 Groups 列表
        this.groups = new ArrayList<>();
        Groups group = new Groups();
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.setGroupHead(groupHead);
        this.groups.add(group);
    }


    public String getUserid() {
        return userId;
    }

    public void setUserid(String userid) {
        this.userId = userid;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public List<com.like4u.server.infrastructrue.po.Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<com.like4u.server.infrastructrue.po.Groups> groups) {
        this.groups = groups;
    }
}
