package com.example.cchat.dao;

import com.example.cchat.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    //查询父级评论
    List<Comment> findByNewsIdParentIdNull(@Param("newsId") Integer newsId, @Param("newsParentId") Integer newsParentId,
                                           @Param("page") Integer page,@Param("size") Integer size);

    //查询一级回复
    List<Comment> findByNewsIdParentIdNotNull(@Param("newsId") Integer newsId, @Param("id") Integer id);

    //查询二级回复
    List<Comment> findByNewsIdAndReplayId(@Param("newsId") Integer newsId,@Param("childId") Integer childId);

    //添加一个评论
    int saveComment(Comment comment);

    //删除评论
    int deleteComment(Integer id);

    List<Comment> getTotal(@Param("newsId")Integer newsId,@Param("newsParentId") Integer newsParentId);

    Comment findNewsById(Integer id);

    int updataComment(Comment comment);
}
