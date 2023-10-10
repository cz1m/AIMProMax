package com.like4u.agreement.message;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/9 21:16
 */
public class SearchFriendRequestMessage extends Message{
    private String userId;
    private String searchKey;

    public SearchFriendRequestMessage(String userId, String searchKey) {
        this.userId = userId;
        this.searchKey = searchKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public int getMessageType() {
        return SearchFriendRequestMessage;
    }
}
