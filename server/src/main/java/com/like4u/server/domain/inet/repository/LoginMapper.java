package com.like4u.server.domain.inet.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.like4u.server.infrastructrue.po.LoginDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 21:50
 */
@Mapper
public interface LoginMapper extends BaseMapper<LoginDto> {
    LoginDto loginCheck(@Param("dto") LoginDto loginDto);
}
