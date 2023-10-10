package com.like4u.agreement.message;

import com.like4u.agreement.protocol.dto.UserDto;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/9 21:16
 */
public class SearchFriendResponseMessage extends Message{

    private List<UserDto> list;

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    @Override
    public int getMessageType() {
        return SearchFriendResponseMessage;
    }
}
