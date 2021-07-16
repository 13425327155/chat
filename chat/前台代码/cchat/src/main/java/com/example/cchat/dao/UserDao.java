package com.example.cchat.dao;

import com.example.cchat.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    //查找所有用户
    List<User> findAllUsers();

    //分页
    Long getTotal();
    List<User> findByPage(@Param("page") Integer page, @Param("size") Integer size);
    //搜索
    User findById(@Param("id") int id);
    List<User> findByName(@Param("user_name") String user_name);
    //删除
    void deleteById(@Param("id") int id);

    //通过学生学号查询用户信息
    User getUserByUserNum(@Param("user_number") String user_number);
    //插入User信息
    void insertUser(@Param("user") User user);
    //更新用户信息
    void updateUserInfo(@Param("user") User user);
}
