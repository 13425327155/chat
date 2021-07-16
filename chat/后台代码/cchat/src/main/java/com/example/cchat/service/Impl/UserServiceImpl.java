package com.example.cchat.service.Impl;

import com.example.cchat.dao.UserDao;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.entity.User;
import com.example.cchat.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public Long getTotal() {
        return userDao.getTotal();
    }

    @Override
    public RespPageBean findByPage(Integer page, Integer size) {
        if(page!=null&&size!=null){
            page=(page-1)*size;
        }
        List<User> data=userDao.findByPage(page,size);
        Long total=userDao.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData_user(data);
        bean.setTotal_user(total);
        return bean;
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findByName(String user_name) {
        return userDao.findByName(user_name);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

}
