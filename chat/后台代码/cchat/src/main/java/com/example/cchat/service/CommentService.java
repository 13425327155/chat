package com.example.cchat.service;

import com.example.cchat.entity.Comment;
import com.example.cchat.entity.RespPageBean;

import java.util.List;

public interface CommentService {
    //用户
    //根据id查询评论信息
    RespPageBean listCommentByNewsId(Integer newsId, Integer page, Integer size);

    //添加保存评论
    int saveComment(Comment comment);

    //删除评论
    int deleteComment(Integer id);


    //分页
    List<Comment> getCommentByPage(Long current, Long size);

    //管理员
    //分页评论
    List<Comment> findStudy();

    //分页
    Long getTotal();
    RespPageBean findByPage(Integer page, Integer size);
    //搜索
    Comment findById(int id);

    //删除评论
    int deleteComment(int id);
}
