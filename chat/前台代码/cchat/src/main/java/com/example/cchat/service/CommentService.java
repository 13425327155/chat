package com.example.cchat.service;

import com.example.cchat.entity.Comment;
import com.example.cchat.entity.RespPageBean;

import java.util.List;

public interface CommentService {
    //根据id查询评论信息
    RespPageBean listCommentByNewsId(Integer newsId, Integer page, Integer size);

    //添加保存评论
    int saveComment(Comment comment);

    //删除评论
    int deleteComment(Integer id);

//    List<Comment> findCommentByNickname(String nickname);

    //分页
    List<Comment> getCommentByPage(Long current, Long size);
}
