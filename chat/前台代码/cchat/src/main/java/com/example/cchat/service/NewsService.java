package com.example.cchat.service;

import com.example.cchat.entity.News;
import com.example.cchat.entity.RespPageBean;

import java.util.List;

public interface NewsService {
    //所有新闻
    List<News> findAllNews();

    RespPageBean findByPage(Integer page, Integer size);

    //添加，修改
    void addNews(News news);

    News findById(int id);

    void deleteById(int id);

    List<News> findByTitle(String n_title);

    List<News> findHotNew();

}
