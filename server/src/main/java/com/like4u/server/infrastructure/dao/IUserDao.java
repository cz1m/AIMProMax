package com.like4u.server.infrastructure.dao;

import com.like4u.server.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/4 9:33
 */
@Mapper
public interface IUserDao {

    String queryUserPassword(@Param("userId") String userId);
    User queryUserById(@Param("userId") String userId);

    List<User> queryFuzzyUserList(@Param("userId") String userId,@Param("searchKey") String searchKey);

   /* Long queryChannelUserCount(ChannelUserReq req);

    List<User> queryChannelUserList(ChannelUserReq req);*/

}
