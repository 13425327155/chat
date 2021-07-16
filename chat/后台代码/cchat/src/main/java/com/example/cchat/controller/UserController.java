package com.example.cchat.controller;

import com.example.cchat.entity.RespPageBean;
import com.example.cchat.entity.User;
import com.example.cchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAllUsers();
    }

    @GetMapping("/findByPage/{page}/{size}")
    public RespPageBean findByPage(@PathVariable("page") Integer page,
                                   @PathVariable("size") Integer size) {
        return userService.findByPage(page, size);
    }

    @GetMapping("/findByName/{user_name}")
    public List<User> findByName(@PathVariable("user_name") String user_name){
        return userService.findByName(user_name);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        userService.deleteById(id);
        if (userService.findById(id) == null){
            return "delete success!";
        }else return "delete error!";
    }
}
