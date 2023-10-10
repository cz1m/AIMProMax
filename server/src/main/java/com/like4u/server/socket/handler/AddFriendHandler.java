package com.like4u.server.socket.handler;

import com.like4u.agreement.message.AddFriendRequestMessage;
import com.like4u.agreement.message.AddFriendResponseMessage;
import com.like4u.server.application.UserService;
import com.like4u.server.domain.user.model.UserInfo;
import com.like4u.server.infrastructrue.comon.SocketChannelUtil;
import com.like4u.server.infrastructrue.po.UserFriend;
import com.like4u.server.socket.MyBizHandler;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/9 21:52
 */
public class AddFriendHandler extends MyBizHandler<AddFriendRequestMessage> {


    public AddFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    //添加好友
    public void channelRead(Channel channel, AddFriendRequestMessage msg) {

        //添加好友要考虑用户好友表：主要是要A->B AND B->A
        List<UserFriend> userFriendList = new ArrayList<>();

        userFriendList.add(new UserFriend(msg.getUserId(),msg.getFriendId()));
        userFriendList.add(new UserFriend(msg.getFriendId(),msg.getUserId()));
        userService.addUserFriend(userFriendList);

        //返回B的信息给A
        UserInfo userInfo = userService.queryUserInfo(msg.getFriendId());
        channel.writeAndFlush(new AddFriendResponseMessage(userInfo.getUserId(),
                userInfo.getUserNickName(),userInfo.getUserHead()));

        //返回A的信息给B
        Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendId());
        if (friendChannel==null) return;
        UserInfo friendInfo = userService.queryUserInfo(msg.getUserId());
        friendChannel.writeAndFlush(new AddFriendResponseMessage(friendInfo.getUserId(),
                friendInfo.getUserNickName() ,friendInfo.getUserHead()));

    }
}
