package com.example.cchat.service;

import com.example.cchat.entity.FeedBack;
import com.example.cchat.entity.RespPageBean;

import java.util.List;

public interface FeedBackServcie {

    //查找所有反馈
    List<FeedBack> findAllFeedBack();

    //分页
    Long getTotal();
    RespPageBean findByPage(Integer page, Integer size);
    //搜索
    FeedBack findById(int id);

    //修改
    int altFeedBack(FeedBack feedBack);

}
