package com.example.cchat.controller;

import com.example.cchat.entity.FeedBack;
import com.example.cchat.service.FeedBackService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
public class FeedBackController {
    @Resource
    private FeedBackService feedBackService;

    @PostMapping("/api/commitFeedBack")
    public boolean commitFeedBack(@RequestBody FeedBack feedBack){
        if (feedBack.getUser_id() == 0){
            return false;
        }
        return feedBackService.commitFeedBack(feedBack);
    }

}
