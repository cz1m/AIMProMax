package com.like4u.server.socket.handler;

import com.like4u.agreement.message.SearchFriendRequestMessage;
import com.like4u.agreement.message.SearchFriendResponseMessage;
import com.like4u.agreement.protocol.dto.UserDto;
import com.like4u.server.application.UserService;
import com.like4u.server.domain.user.model.LuckUserInfo;
import com.like4u.server.socket.MyBizHandler;
import io.netty.channel.Channel;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/9 21:36
 */
public class SearchFriendHandler extends MyBizHandler<SearchFriendRequestMessage> {
    public SearchFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, SearchFriendRequestMessage msg) {

        logger.info("搜索好友请求 处理：{}", JSON.toJSONString(msg));
        List<UserDto> userDtoList = new ArrayList<>();
        List<LuckUserInfo> userInfoList = userService.queryFuzzyUserInfoList(msg.getUserId(), msg.getSearchKey());
        for (LuckUserInfo userInfo : userInfoList) {
            UserDto userDto = new UserDto();
            userDto.setUserId(userInfo.getUserId());
            userDto.setUserNickName(userInfo.getUserNickName());
            userDto.setUserHead(userInfo.getUserHead());
            userDto.setStatus(userInfo.getStatus());
            userDtoList.add(userDto);
        }
        SearchFriendResponseMessage response = new SearchFriendResponseMessage();
        response.setList(userDtoList);
        channel.writeAndFlush(response);
    }
}
