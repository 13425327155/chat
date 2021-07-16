package com.example.cchat.service.Impl;

import com.example.cchat.dao.CommentDao;
import com.example.cchat.entity.Comment;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    //用户
    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public RespPageBean listCommentByNewsId(Integer newsId, Integer page, Integer size) {
        RespPageBean bean = new RespPageBean();
        if(page!=null&&size!=null){
            page=(page-1)*size;
        }
        //查询出父节点
        List<Comment> comments = commentDao.findByNewsIdParentIdNull(newsId, newsId, page, size);
        List<Comment> comments2 = commentDao.UsergetTotal(newsId,newsId);
        for(Comment comment : comments){
            Integer id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentDao.findByNewsIdParentIdNotNull(newsId,id);
            //查询出子评论
            combineChildren(newsId, childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        bean.setData_usercomment(comments);

        bean.setTotal_usercomment(comments2.size());
        return bean;
    }

    /**
     * @Description: 查询出子评论
     * @Param: childComments：所有子评论
     * @Param: parentNickname1：父评论姓名
     * @Return:
     */
    private void combineChildren(Integer newsId, List<Comment> childComments, String parentNickname1) {
        //判断是否有一级子评论
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Integer childId = childComment.getId();
                //查询出子二级评论
                recursively(newsId, childId, parentNickname);
            }
        }
    }

    /**
     * @Description: 循环迭代找出子集回复
     * @Param: chileId:子评论id
     * @Param: parentNickname1:子评论姓名
     * @Return:
     */
    private void recursively(Integer newsId, Integer childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentDao.findByNewsIdAndReplayId(newsId,childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Integer replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(newsId,replayId,parentNickname);
            }
        }
    }


    @Override
    public int saveComment(Comment comment) {
        if (comment.getNews_id() == -1)
            comment.setC_type("学习");
        else if (comment.getNews_id() == -2)
            comment.setC_type("就业");
        else if (comment.getNews_id() == -3)
            comment.setC_type("交友");
        else if (comment.getNews_id() == -4)
            comment.setC_type("打听");
        else if(comment.getNews_id() == -5)
            comment.setC_type("其他");
        comment.setC_time(new Date());
        return commentDao.saveComment(comment);
    }

    @Override
    public int deleteComment(Integer id) {
        Comment comment = commentDao.findNewsById(id);
        comment.setC_content("已删除");
        return commentDao.updataComment(comment);
    }


    @Override
    public List<Comment> getCommentByPage(Long current, Long size) {
        return null;
    }



    //管理员
    @Override
    public List<Comment> findStudy() {
        return commentDao.findStudy();
    }

    @Override
    public Long getTotal() {
        return commentDao.getTotal();
    }

    @Override
    public RespPageBean findByPage(Integer page, Integer size) {
        if(page!=null&&size!=null){
            page=(page-1)*size;
        }
        List<Comment> data=commentDao.findByPage(page,size);
        Long total=commentDao.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData_comment(data);
        bean.setTotal_comment(total);
        return bean;
    }

    @Override
    public Comment findById(int id) {
        return commentDao.findById(id);
    }

    @Override
    public int deleteComment(int id) {
        return commentDao.deleteComment(id);
    }
}
