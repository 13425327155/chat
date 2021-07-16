package com.example.cchat.controller;

import com.example.cchat.entity.Comment;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //用户
    //根据id查询评论
    @GetMapping("/getComment/{newsId}/{page}/{size}")
    public RespPageBean getComment(@PathVariable("newsId") Integer newsId,
                                   @PathVariable("page") Integer page,
                                   @PathVariable("size")Integer size) {

        return commentService.listCommentByNewsId(newsId,page,size);
    }
    //新增评论
    @PostMapping("/addComment")
    public int addComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }
    //删除评论
    @DeleteMapping("/deleteComment/{id}")
    public int delete(@PathVariable Integer id){
        return commentService.deleteComment(id);
    }


    //管理员
    @RequestMapping("/findStudy")
    public List<Comment> findStudy(){
        return commentService.findStudy();
    }

    @GetMapping("/findByPage/{page}/{size}")
    public RespPageBean findByPage(@PathVariable("page") Integer page,
                                   @PathVariable("size") Integer size) {
        return commentService.findByPage(page, size);
    }

    @GetMapping("/findById/{id}")
    public Comment findById(@PathVariable("id") int id){
        return commentService.findById(id);
    }

    @GetMapping("/alt/{id}")
    public String altFeedBack(@PathVariable("id") int id){
        int altResult = commentService.deleteComment(id);
        if (altResult == 1){
            return "alt success!";
        }else return "alt error!";
    }
}
