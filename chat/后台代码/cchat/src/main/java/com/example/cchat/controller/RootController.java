package com.example.cchat.controller;

import com.example.cchat.entity.Root;
import com.example.cchat.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class RootController {

    @Autowired
    private RootService rootService;

    @PostMapping("/login") 
    public String login(@RequestBody Root root, HttpSession session){
        Root checkRoot = rootService.checkRoot(root);
        if (checkRoot != null) {
            //存值
            session.setAttribute("root", root);
            return "login success!";
        }
        else{
            return "login error!";
        }
    }
}
