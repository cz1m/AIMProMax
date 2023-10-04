package com.like4u.server.infrastructure.dao;

import com.like4u.server.infrastructure.po.Groups;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface IGroupsDao {

    Groups queryGroupsById(@Param("groupId") String groupsId);

}
