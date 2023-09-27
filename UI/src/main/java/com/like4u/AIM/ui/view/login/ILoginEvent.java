package com.like4u.AIM.ui.view.login;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/26 21:51
 */
public interface ILoginEvent {
    /**
     * 登录校验
     * @param userId 用户Id
     * @param userPassword 密码
     *      未来会传入IP地址、设备信息、登录时间
     */
    void doLoginCheck(String userId,String userPassword);

}
