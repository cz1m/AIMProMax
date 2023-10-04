package com.like4u.server.infrastructure.dao;

import com.like4u.server.infrastructure.po.ChatRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;


@Mapper
public interface IChatRecordDao {

    void appendChatRecord(@Param("req") ChatRecord req);

    List<ChatRecord> queryUserChatRecordList(@Param("talkId") String talkId,@Param("userId") String userId);

    List<ChatRecord> queryGroupsChatRecordList(@Param("talkId") String talkId,@Param("userId") String userId);

}
