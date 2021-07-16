package com.example.cchat.controller;

import com.example.cchat.entity.Comment;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.entity.User;
import com.example.cchat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    //根据id查询评论
    @GetMapping("/getComment/{newsId}/{page}/{size}")
    public RespPageBean getComment(@PathVariable("newsId") Integer newsId,
                                   @PathVariable("page") Integer page,
                                   @PathVariable("size")Integer size) {
//        User user = (User) session.getAttribute("userInfo");
//        System.out.println(user.toString());
        return commentService.listCommentByNewsId(newsId,page,size);
    }

    //新增评论
    @PostMapping("/addComment")
    public int addComment(@RequestBody Comment comment, HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        System.out.println(user.toString());
        return commentService.saveComment(comment);
//        Integer newsId = comment.getNews_id();
//        if (authentication != null){
//            TUser user = (TUser)authentication.getPrincipal();
//            tComment.setAvatar(user.getAvatar());
//            tComment.setAdminComment(true);
//            tComment.setIp(ip);
//        }
//        else {
//            //设置头像
//            tComment.setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2070453827,1163403148&fm=26&gp=0.jpg");
//            tComment.setIp(ip);
//        }
//        if (tComment.getParentComment() != null) {
//            tComment.setParentCommentId(tComment.getParentComment().getId());
//        }

//        User user = (User) session.getAttribute("userInfo");
//        System.out.println(user.toString());
//        if (user != null){
//            comment.setUser_id(user.getId());
//            comment.setNickname(user.getUser_name());
//            comment.setImg(user.getUser_img());
//            commentService.saveComment(comment);
//            return true;
//        }else {
//            return false;
//        }
    }

    //删除评论
    @DeleteMapping("/deleteComment/{id}")
    public int delete(@PathVariable Integer id){
        return commentService.deleteComment(id);
    }


}
