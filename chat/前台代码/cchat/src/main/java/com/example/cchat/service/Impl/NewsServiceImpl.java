package com.example.cchat.service.Impl;

import com.example.cchat.dao.NewsDao;
import com.example.cchat.entity.News;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsDao newsDao;

    @Override
    public List<News> findAllNews() {
        return newsDao.findAllNews();
    }

    @Override
    public RespPageBean findByPage(Integer page, Integer size) {
        if(page!=null&&size!=null){
            page=(page-1)*size;
        }
        List<News> data=newsDao.findByPage(page,size);
        Long total=newsDao.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData_news(data);
        bean.setTotal_news(total);
        return bean;
    }

    @Override
    public void addNews(News news) {
        newsDao.addNews(news);
    }

    @Override
    public News findById(int id) {
        News news = newsDao.findById(id);
        int view = Integer.parseInt(news.getN_views()) + 1;
        news.setN_views(String.valueOf(view));
        newsDao.updataNews(news);
        return newsDao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        newsDao.deleteById(id);
    }

    @Override
    public List<News> findByTitle(String n_title) {
        return newsDao.findByTitle(n_title);
    }

    @Override
    public List<News> findHotNew() {
        return newsDao.findHotNew();
    }
}
