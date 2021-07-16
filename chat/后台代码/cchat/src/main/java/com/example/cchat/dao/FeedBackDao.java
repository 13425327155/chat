package com.example.cchat.dao;

import com.example.cchat.entity.FeedBack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedBackDao {
    //查找所有反馈
    List<FeedBack> findAllFeedBack();

    //分页
    Long getTotal();
    List<FeedBack> findByPage(@Param("page") Integer page, @Param("size") Integer size);

    FeedBack findById(@Param("id") int id);

    //添加修改
    int altFeedBack(FeedBack feedBack);
}
