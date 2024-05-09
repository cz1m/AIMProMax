package com.like4u.server.domain.inet.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.like4u.server.infrastructrue.po.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 17:30
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> findByUsername(String username);


}
