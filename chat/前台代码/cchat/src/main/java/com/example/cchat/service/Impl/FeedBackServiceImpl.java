package com.example.cchat.service.Impl;


import com.example.cchat.dao.FeedBackDao;
import com.example.cchat.entity.FeedBack;
import com.example.cchat.service.FeedBackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeedBackServiceImpl implements FeedBackService {
    @Resource
    private FeedBackDao feedBackDao;

    @Override
    public boolean commitFeedBack(FeedBack feedBack) {
        if (feedBack != null){
            feedBackDao.commitFeedBack(feedBack);
            return true;
        }else {
            return false;
        }
    }
}
