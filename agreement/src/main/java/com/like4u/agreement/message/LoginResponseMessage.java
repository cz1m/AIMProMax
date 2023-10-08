package com.like4u.agreement.message;

import com.like4u.agreement.protocol.dto.ChatTalkDto;
import com.like4u.agreement.protocol.dto.GroupsDto;
import com.like4u.agreement.protocol.dto.UserFriendDto;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class LoginResponseMessage extends AbstractResponseMessage {

    private boolean success;                    // 登陆反馈
    private String userId;                      // 用户ID
    private String userHead;                    // 用户头像
    private String userNickName;                // 用户昵称
    private List<ChatTalkDto> chatTalkList = new ArrayList<>();     // 聊天对话框数据[success is true]
    private List<GroupsDto> groupsList = new ArrayList<>();         // 群组列表
    private List<UserFriendDto> userFriendList = new ArrayList<>(); // 好友列表
    public LoginResponseMessage(Boolean isSuccess, String message){
        super(isSuccess, message);
    };

    public LoginResponseMessage(boolean success) {
        this.success = success;
    }
    public boolean isSuccess() {
        return success;
    }

    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
