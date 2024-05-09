package com.like4u.server.socket.handler;

import com.like4u.agreement.message.AddFriendRequestMessage;
import com.like4u.agreement.message.GroupMemberRequest;
import com.like4u.agreement.message.GroupMemberResponse;
import com.like4u.agreement.protocol.dto.UserFriendDto;
import com.like4u.server.application.UserService;
import com.like4u.server.domain.user.model.UserFriendInfo;
import com.like4u.server.socket.MyBizHandler;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理客户端查询群聊全部成员的请求
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/5/5 17:36
 */
public class GroupMemberHandler extends MyBizHandler<GroupMemberRequest> {
    public GroupMemberHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, GroupMemberRequest msg) {
        System.out.println("收到客户端查询全部群成员的请求"+msg.getGroupId());
        String groupId = msg.getGroupId();

        GroupMemberResponse groupMemberResponse = new GroupMemberResponse(true);
        List<UserFriendInfo> groupMembers = userService.queryGroupMembers(groupId);
        List<UserFriendDto> userFriendList =new ArrayList<>();

        for (UserFriendInfo userInfo : groupMembers) {
            UserFriendDto userFriendDto = new UserFriendDto(userInfo.getFriendId(),userInfo.getFriendName(),userInfo.getFriendHead());
            userFriendList.add(userFriendDto);
        }
        groupMemberResponse.setUserFriendList(userFriendList);

        channel.writeAndFlush(groupMemberResponse);
    }
}
