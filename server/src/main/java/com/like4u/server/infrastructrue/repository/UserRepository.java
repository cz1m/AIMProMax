package com.like4u.server.infrastructrue.repository;

import com.like4u.server.domain.user.model.*;
import com.like4u.server.infrastructrue.dao.*;
import com.like4u.server.infrastructrue.po.*;
import com.like4u.server.domain.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.like4u.server.infrastructrue.comon.Constants.TalkType.Friend;
import static com.like4u.server.infrastructrue.comon.Constants.TalkType.Group;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/7 21:47
 */
@Repository("userRepository")
public class UserRepository implements IUserRepository {

    @Autowired
    private IChatRecordDao chatRecordDao;
    @Autowired
    private IGroupsDao groupsDao;
    @Autowired
    private ITalkBoxDao talkBoxDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserFriendDao userFriendDao;
    @Autowired
    private IUserGroupDao userGroupDao;

    @Override
    public String queryUserPassword(String userId) {
        return userDao.queryUserPassword(userId);
    }

    /**
     * 查询用户信息
     * @param userId 用户ID
     * @return 用户的数据包
     */
    @Override
    public UserInfo queryUserInfo(String userId) {

        User user = userDao.queryUserById(userId);
        return new UserInfo(user.getUserId(),user.getUserNickName(),user.getUserHead());
    }

    /**
     * 查询用户的对话框
     * @param userId 个人用户ID
     * @return 当前用户的对话框信息（talkBoxInfo）列表
     */
    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {

        List<TalkBoxInfo> talkBoxInfoList = new ArrayList<>();

        List<TalkBox> talkBoxes = talkBoxDao.queryTalkBoxList(userId);//查询用户的全部对话框
        for (TalkBox talkBox:talkBoxes){
            TalkBoxInfo talkBoxInfo = new TalkBoxInfo();
            if (Friend.getCode().equals(talkBox.getTalkType())){
                User user = userDao.queryUserById(talkBox.getTalkId());//如果是用户对话框，查出对应用户信息

                talkBoxInfo.setTalkType(Friend.getCode());
                talkBoxInfo.setTalkId(user.getUserId());
                talkBoxInfo.setTalkName(user.getUserNickName());
                talkBoxInfo.setTalkHead(user.getUserHead());
            }else if (Group.getCode().equals(talkBox.getTalkType())){
                Groups groups = groupsDao.queryGroupsById(talkBox.getTalkId());

                talkBoxInfo.setTalkType(Group.getCode());
                talkBoxInfo.setTalkHead(groups.getGroupHead());
                talkBoxInfo.setTalkName(groups.getGroupName());
                talkBoxInfo.setTalkId(groups.getGroupId());
            }
            talkBoxInfoList.add(talkBoxInfo);
        }

        return talkBoxInfoList;
    }

    @Override
    public void addTalkBoxInfo(String userId, String talkId, Integer talkType) {
        if(talkBoxDao.queryTalkBox(userId,talkId)!=null) return;
        TalkBox talkBox = new TalkBox();
        talkBox.setUserId(userId);
        talkBox.setTalkId(talkId);
        talkBox.setTalkType(talkType);
        talkBoxDao.addTalkBox(talkBox);

    }

    @Override
    public List<UserFriendInfo> queryUserFriendInfoList(String userId) {

        List<UserFriendInfo> userFriendInfoList = new ArrayList<>();

        List<String> friendIdList = userFriendDao.queryUserFriendIdList(userId);
        for (String friendId : friendIdList) {
            User user = userDao.queryUserById(friendId);
            UserFriendInfo friendInfo = new UserFriendInfo();
            friendInfo.setFriendId(user.getUserId());
            friendInfo.setFriendName(user.getUserNickName());
            friendInfo.setFriendHead(user.getUserHead());
            userFriendInfoList.add(friendInfo);
        }


        return userFriendInfoList;
    }

    @Override
    public List<GroupsInfo> queryUserGroupInfoList(String userId) {
        List<GroupsInfo> groupsInfoList = new ArrayList<>();
        List<String> groupsIdList = userGroupDao.queryUserGroupsIdList(userId);
        for (String groupsId : groupsIdList) {
            Groups groups = groupsDao.queryGroupsById(groupsId);
            GroupsInfo groupsInfo = new GroupsInfo();
            groupsInfo.setGroupId(groups.getGroupId());
            groupsInfo.setGroupName(groups.getGroupName());
            groupsInfo.setGroupHead(groups.getGroupHead());
            groupsInfoList.add(groupsInfo);
        }
        return groupsInfoList;
    }

    /**
     * userId用户通过key模糊查询用户
     * 将查询出的用户列表通过用户好友关系表查询好友关系后返回LuckUserInfo
     * @param userId    用户ID
     * @param searchKey 用户名、用户ID
     * @return 模糊查询后的用户展示表
     */
    @Override
    public List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey) {
        List<LuckUserInfo> luckUserInfoList = new ArrayList<>();
        List<User> userList = userDao.queryFuzzyUserList(userId, searchKey);
        for (User user : userList) {
            LuckUserInfo userInfo = new LuckUserInfo(user.getUserId(), user.getUserNickName(), user.getUserHead(), 0);
            UserFriend req = new UserFriend();
            req.setUserId(userId);
            req.setUserFriendId(user.getUserId());
            UserFriend userFriend = userFriendDao.queryUserFriendById(req);
            if (null != userFriend) {
                userInfo.setStatus(2);
            }
            luckUserInfoList.add(userInfo);
        }
        return luckUserInfoList;
    }

    @Override
    public void addUserFriend(List<UserFriend> userFriendList) {

            userFriendDao.addUserFriendList(userFriendList);

    }

    @Override
    public void appendChatRecord(ChatRecordInfo chatRecordInfo) {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setUserId(chatRecordInfo.getUserId());
        chatRecord.setFriendId(chatRecordInfo.getFriendId());
        chatRecord.setMsgContent(chatRecordInfo.getMsgContent());
        chatRecord.setMsgType(chatRecordInfo.getMsgType());
        chatRecord.setMsgDate(chatRecordInfo.getMsgDate());
        chatRecord.setTalkType(chatRecordInfo.getTalkType());
        chatRecordDao.appendChatRecord(chatRecord);
    }

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        List<ChatRecordInfo> chatRecordInfoList = new ArrayList<>();
        List<ChatRecord> list = new ArrayList<>();
        if (Friend.getCode().equals(talkType)){
            list = chatRecordDao.queryUserChatRecordList(talkId, userId);
        } else if (Group.getCode().equals(talkType)){
            list =  chatRecordDao.queryGroupsChatRecordList(talkId, userId);
        }
        for (ChatRecord chatRecord : list) {
            ChatRecordInfo chatRecordInfo = new ChatRecordInfo();
            chatRecordInfo.setUserId(chatRecord.getUserId());
            chatRecordInfo.setFriendId(chatRecord.getFriendId());
            chatRecordInfo.setMsgContent(chatRecord.getMsgContent());
            chatRecordInfo.setMsgType(chatRecord.getMsgType());
            chatRecordInfo.setMsgDate(chatRecord.getMsgDate());
            chatRecordInfoList.add(chatRecordInfo);
        }
        return chatRecordInfoList;
    }

    @Override
    public void deleteUserTalk(String userId, String talkId) {
        talkBoxDao.deleteUserTalk(userId, talkId);
    }

    @Override
    public List<String> queryUserGroupsIdList(String userId) {
        return userGroupDao.queryUserGroupsIdList(userId);
    }

    @Override
    public List<String> queryTalkBoxGroupsIdList(String userId) {
        return talkBoxDao.queryTalkBoxGroupsIdList(userId);
    }
}
