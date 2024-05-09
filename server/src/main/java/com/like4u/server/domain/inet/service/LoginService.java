package com.like4u.server.domain.inet.service;


import com.like4u.server.infrastructrue.po.LoginDto;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 21:50
 */
public interface LoginService {
    LoginDto loginCheck(LoginDto loginDto);
}
