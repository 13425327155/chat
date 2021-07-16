package com.example.cchat.dao;

import com.example.cchat.entity.News;
import com.example.cchat.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewsDao {
    //查找所有新闻
    List<News> findAllNews();

    //分页
    Long getTotal();
    List<News> findByPage(@Param("page") Integer page, @Param("size") Integer size);
    //搜索
    News findById(@Param("id") int id);
    List<News> findByTitle(@Param("n_title") String n_title);
    //添加修改
    void addNews(News news);
    //删除
    void deleteById(@Param("id") int id);

    List<News> findHotNew();

    void updataNews(News news);
}
