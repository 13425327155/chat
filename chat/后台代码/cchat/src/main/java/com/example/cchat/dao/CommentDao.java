package com.example.cchat.dao;

import com.example.cchat.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    //用户
    //查询父级评论
    List<Comment> findByNewsIdParentIdNull(@Param("newsId") Integer newsId, @Param("newsParentId") Integer newsParentId,
                                           @Param("page") Integer page,@Param("size") Integer size);

    //查询一级回复
    List<Comment> findByNewsIdParentIdNotNull(@Param("newsId") Integer newsId, @Param("id") Integer id);

    //查询二级回复
    List<Comment> findByNewsIdAndReplayId(@Param("newsId") Integer newsId,@Param("childId") Integer childId);

    //添加一个评论
    int saveComment(Comment comment);

    List<Comment> UsergetTotal(@Param("newsId")Integer newsId,@Param("newsParentId") Integer newsParentId);

    Comment findNewsById(Integer id);

    int updataComment(Comment comment);

    List<Comment> findStudy();

    //管理员
    //分页
    Long getTotal();
    List<Comment> findByPage(@Param("page") Integer page, @Param("size") Integer size);

    Comment findById(@Param("id") int id);

    //删除评论
    int deleteComment(@Param("id") int id);
}
