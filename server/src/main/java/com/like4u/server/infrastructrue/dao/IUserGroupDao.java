package com.like4u.server.infrastructrue.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserGroupDao {

    List<String> queryUserGroupsIdList(@Param("userId") String userId);

}
