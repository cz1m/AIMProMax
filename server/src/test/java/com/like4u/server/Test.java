package com.like4u.server;

import com.like4u.server.application.UserService;
import com.like4u.server.infrastructrue.dao.IUserDao;
import com.like4u.server.infrastructrue.dao.IUserFriendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/9 16:24
 */
@SpringBootTest
public class Test {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserFriendDao userFriendDao;
    @Autowired
    private UserService userService;

   /* @Test
    public void test_queryFuzzyUserList() {
        List<User> userList = userDao.queryFuzzyUserList("184172133", "");
        System.out.println(JSON.toJSONString(userList));
    }

    @Test
    public void test_addUserFriendList() {
        List<UserFriend> userFriendList = new ArrayList<>();
        userFriendList.add(new UserFriend("11", "22"));
        userFriendList.add(new UserFriend("22", "11"));
        userFriendDao.addUserFriendList(userFriendList);
    }

    @Test
    public void test_asyncAppendChatRecord() throws InterruptedException {

        ChatRecordInfo chatRecordInfo = new ChatRecordInfo();
        chatRecordInfo.setUserId("184172133");
        chatRecordInfo.setFriendId("523088136");
        chatRecordInfo.setMsgContent("我是哈尼克兔你在吗");
        chatRecordInfo.setMsgDate(new Date());
        userService.asyncAppendChatRecord(chatRecordInfo);
        Thread.sleep(10000);
    }
*/
}
