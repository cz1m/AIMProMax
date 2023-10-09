package com.like4u.server;

import com.like4u.server.infrastructrue.dao.IUserDao;
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

    @org.junit.jupiter.api.Test
    public void test(){

        String s = userDao.queryUserPassword("184172133");
        System.out.println(s);
    }



}
