package com.like4u.client.socket.handler;

import com.like4u.AIM.ui.util.SafeDoubleCheckSingleton;
import com.like4u.AIM.ui.view.chat.ChatController;
import com.like4u.AIM.ui.view.chat.element.group_bar_friend.GroupMember;
import com.like4u.agreement.message.GroupMemberResponse;
import com.like4u.agreement.protocol.dto.UserFriendDto;
import com.like4u.client.application.UIService;
import com.like4u.client.socket.MyBizHandler;
import io.netty.channel.Channel;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/5/5 21:25
 */
public class GroupMemberHandler extends MyBizHandler<GroupMemberResponse> {


    public GroupMemberHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, GroupMemberResponse msg) {
        SafeDoubleCheckSingleton lock = SafeDoubleCheckSingleton.getInstance();
        System.out.println("handler lock is" +lock);
        synchronized (lock) {
            System.out.println("当前线程"+Thread.currentThread().getName());
            System.out.println("收到服务器消息 ");
        msg.getUserFriendList().forEach(System.out::println);

        List<UserFriendDto> userFriendList = msg.getUserFriendList();
        for (UserFriendDto userFriendDto : userFriendList) {
            System.out.println("进入循环");
            System.out.println("获取不到ChatController？"+ChatController.userFriendList);
            ChatController.userFriendList.add(new GroupMember(
                    userFriendDto.getFriendId(),userFriendDto.getFriendName(), userFriendDto.getFriendHead()
            ));
            System.out.println("循环赋值"+userFriendDto);
        }
        System.out.println("打印controller中的静态变量的值");
        ChatController.userFriendList.forEach(System.out::println);


            lock.notify();
        }
    }
}
