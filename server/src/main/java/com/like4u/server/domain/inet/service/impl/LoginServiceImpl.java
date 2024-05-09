package com.like4u.server.domain.inet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.like4u.server.domain.inet.repository.LoginMapper;
import com.like4u.server.domain.inet.service.LoginService;
import com.like4u.server.infrastructrue.po.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 21:50
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, LoginDto> implements LoginService {

    @Autowired
    private LoginMapper mapper;
    @Override
    public LoginDto loginCheck(LoginDto loginDto) {
        LoginDto user =mapper.loginCheck(loginDto);
        if (user ==null){
            throw new RuntimeException(("用户名密码错误"));
        }
        return user;

       /* LambdaQueryWrapper<LoginDto> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LoginDto::getUserId, loginDto.getUserId())
                .eq(LoginDto::getUserPassword,loginDto.getUserPassword());

        LoginDto user = mapper.selectOne(wrapper);
        if (user ==null){
            throw new RuntimeException(("用户名密码错误"));
        }*/
    }
}
