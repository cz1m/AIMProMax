package com.like4u.server.infrastructure.dao;

import com.like4u.server.infrastructure.po.TalkBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;


@Mapper
public interface ITalkBoxDao {

    List<TalkBox> queryTalkBoxList(@Param("userId") String userId);

    void addTalkBox(@Param("talkBox") TalkBox talkBox);

    void deleteUserTalk(@Param("userId") String userId,@Param("talkId") String talkId);

    TalkBox queryTalkBox(@Param("userId") String userId,@Param("talkId") String talkId);

    List<String> queryTalkBoxGroupsIdList(@Param("userId") String userId);

}
