package com.like4u.server.application;



import com.like4u.server.domain.inet.model.ChannelUserInfo;
import com.like4u.server.domain.inet.model.ChannelUserReq;
import com.like4u.server.domain.inet.model.InetServerInfo;

import java.util.List;

/**
 * 网络信息查询
 */
public interface InetService {

    /**
     * 查询netty服务端状态信息
     * @return  服务端状态信息
     */
    InetServerInfo queryNettyServerInfo();

    /**
     * 查询通讯用户数量
     * @param req 用户类
     * @return 用户数量
     */

    Long queryChannelUserCount(ChannelUserReq req);

    /**
     *
     * @param req 要查询的用户
     * @return 用户列表
     */

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}
