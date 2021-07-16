package com.example.cchat.service;

import com.example.cchat.entity.RespPageBean;
import com.example.cchat.entity.User;

import java.util.List;

public interface UserService {
    //查找所有用户
    List<User> findAllUsers();

    //分页
    Long getTotal();
    RespPageBean findByPage(Integer page, Integer size);
    //搜索
    User findById(int id);
    List<User> findByName(String user_name);
    //删除
    void deleteById(int id);


    User getUserByUserNum(String user_number);
    boolean insertUser(User user,String user_number);
    User userLogin(String user_number, String user_password);
    boolean updateUserInfo(User user);
    boolean updatePassword(String old_password, String user_password, String user_number);

}
