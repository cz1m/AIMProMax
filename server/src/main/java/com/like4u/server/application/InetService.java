package com.like4u.server.application;



import com.like4u.server.domain.inet.model.ChannelUserInfo;
import com.like4u.server.domain.inet.model.ChannelUserReq;
import com.like4u.server.domain.inet.model.InetServerInfo;

import java.util.List;

/**
 * 网络信息查询
 */
public interface InetService {

    InetServerInfo queryNettyServerInfo();

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}
