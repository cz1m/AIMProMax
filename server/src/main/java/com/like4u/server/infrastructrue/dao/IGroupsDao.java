package com.like4u.server.infrastructrue.dao;

import com.like4u.server.infrastructrue.dto.UserGroupDto;
import com.like4u.server.infrastructrue.po.Groups;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface IGroupsDao {

    Groups queryGroupsById(@Param("groupId") String groupsId);
    List<UserGroupDto> queryGroupsByUserId(@Param("userId") String userId);

}
