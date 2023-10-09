package com.like4u.server.domain.inet.repository;



import com.like4u.server.domain.inet.model.ChannelUserInfo;
import com.like4u.server.domain.inet.model.ChannelUserReq;

import java.util.List;


public interface IInetRepository {

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}
