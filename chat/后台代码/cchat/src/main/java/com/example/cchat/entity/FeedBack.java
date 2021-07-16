package com.example.cchat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FeedBack {
    private int id;
    private int user_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/ShangHai")
    private Date f_time;
    private String f_content;
    private String f_answer;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getF_time() {
        return f_time;
    }

    public void setF_time(Date f_time) {
        this.f_time = f_time;
    }

    public String getF_content() {
        return f_content;
    }

    public void setF_content(String f_content) {
        this.f_content = f_content;
    }

    public String getF_answer() {
        return f_answer;
    }

    public void setF_answer(String f_answer) {
        this.f_answer = f_answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
