package com.like4u.server.domain.inet.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.like4u.server.infrastructrue.po.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/12 17:06
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDto> {
}
