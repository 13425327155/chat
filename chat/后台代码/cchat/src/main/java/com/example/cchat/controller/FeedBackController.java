package com.example.cchat.controller;

import com.example.cchat.entity.FeedBack;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.service.FeedBackServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedBackController {
    @Autowired
    private FeedBackServcie feedBackServcie;

    @RequestMapping("/findAll")
    public List<FeedBack> findAll(){
        return feedBackServcie.findAllFeedBack();
    }

    @GetMapping("/findByPage/{page}/{size}")
    public RespPageBean findByPage(@PathVariable("page") Integer page,
                                   @PathVariable("size") Integer size) {
        return feedBackServcie.findByPage(page, size);
    }

    @GetMapping("/findById/{id}")
    public FeedBack findById(@PathVariable("id") int id){
        return feedBackServcie.findById(id);
    }

    @PutMapping("/alt")
    public String altFeedBack(@RequestBody FeedBack feedBack){
        int altResult = feedBackServcie.altFeedBack(feedBack);
        if (altResult == 1){
            return "alt success!";
        }else return "alt error!";
    }
}
