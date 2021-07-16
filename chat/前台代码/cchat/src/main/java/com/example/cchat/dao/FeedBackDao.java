package com.example.cchat.dao;

import com.example.cchat.entity.FeedBack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FeedBackDao {
    //提交反馈表单
    void commitFeedBack(@Param("feedBack") FeedBack feedBack);

}
