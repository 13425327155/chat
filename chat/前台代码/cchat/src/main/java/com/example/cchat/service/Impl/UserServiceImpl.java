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


    @Override
    public User getUserByUserNum(String user_number) {
        return userDao.getUserByUserNum(user_number);
    }

    @Override
    public boolean insertUser(User user, String user_number) {
        //查询数据库是否存在user_number，如果存在，不允许注册
        User user1 = userDao.getUserByUserNum(user_number);
        if (user1 != null){
            return false;
        }else {
            userDao.insertUser(user);
            return true;
        }
    }

    @Override
    public User userLogin(String user_number, String user_password) {
        //从user表中查询user_number数据
        User user = userDao.getUserByUserNum(user_number);
        //判断是否符合登录
        if (user != null){
            if (user.getUser_number().equals(user_number) && user.getUser_password().equals(user_password)){
                return user;
            }else {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean updateUserInfo(User user) {
        if (user != null){
            userDao.updateUserInfo(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(String old_password, String user_password, String user_number) {
        User user = userDao.getUserByUserNum(user_number);
        if (user.getUser_password().equals(old_password)){
            user.setUser_password(user_password);
            userDao.updateUserInfo(user);
            return true;
        }
        return false;
    }

}
