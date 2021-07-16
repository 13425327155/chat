package com.example.cchat.service.Impl;

import com.example.cchat.dao.FeedBackDao;
import com.example.cchat.entity.FeedBack;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.service.FeedBackServcie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeedBackServcieImpl implements FeedBackServcie {

    @Resource
    private FeedBackDao feedBackDao;

    @Override
    public List<FeedBack> findAllFeedBack() {
        return feedBackDao.findAllFeedBack();
    }

    @Override
    public Long getTotal() {
        return feedBackDao.getTotal();
    }

    @Override
    public RespPageBean findByPage(Integer page, Integer size) {
        if(page!=null&&size!=null){
            page=(page-1)*size;
        }
        List<FeedBack> data=feedBackDao.findByPage(page,size);
        Long total=feedBackDao.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData_feedback(data);
        bean.setTotal_feedback(total);
        return bean;
    }

    @Override
    public FeedBack findById(int id) {
        return feedBackDao.findById(id);
    }

    @Override
    public int altFeedBack(FeedBack feedBack) {
        return feedBackDao.altFeedBack(feedBack);
    }
}
