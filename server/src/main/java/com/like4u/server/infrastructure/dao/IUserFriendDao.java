package com.like4u.server.infrastructure.dao;

import com.like4u.server.infrastructure.po.UserFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface IUserFriendDao {

    List<String> queryUserFriendIdList(@Param("userId") String userId);

    UserFriend queryUserFriendById(@Param("req") UserFriend req);

    void addUserFriendList(@Param("list") List<UserFriend> list);

}
