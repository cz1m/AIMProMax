package com.like4u.server.infrastructrue.dao;

import com.like4u.server.infrastructrue.po.Groups;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface IGroupsDao {

    Groups queryGroupsById(@Param("groupId") String groupsId);

}
