package com.like4u.server.domain.inet.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.like4u.server.domain.inet.repository.UserInfoMapper;
import com.like4u.server.domain.inet.service.UserInfoService;
import com.like4u.server.infrastructrue.po.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/12 17:05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDto> implements UserInfoService {

    @Autowired
    private UserInfoMapper mapper;
    @Override
    public void updateUserInfo(UserInfoDto userInfoDto) {
        LambdaUpdateWrapper<UserInfoDto> wrapper = new LambdaUpdateWrapper<>();

        wrapper.eq(UserInfoDto::getUsername, userInfoDto.getUsername());

        mapper.update(userInfoDto,wrapper);
    }
}
