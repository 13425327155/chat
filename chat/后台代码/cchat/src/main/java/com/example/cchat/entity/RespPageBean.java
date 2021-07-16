package com.example.cchat.entity;

import java.util.List;

public class RespPageBean
{
    private Long total_user;
    private List<User> data_user;
    private Long total_news;
    private List<News> data_news;
    private Long total_feedback;
    private List<FeedBack> data_feedback;
    private Long total_comment;
    private List<Comment> data_comment;
    private Long total_find;
    private List<Goods> data_find;
    private Long total_lost;
    private List<Goods> data_lost;
    private Integer total_usercomment;
    private List<Comment> data_usercomment;




    public Long getTotal_user() {
        return total_user;
    }

    public void setTotal_user(Long total_user) {
        this.total_user = total_user;
    }


    public List<User> getData_user() {
        return data_user;
    }

    public void setData_user(List<User> data_user) {
        this.data_user = data_user;
    }

    public Long getTotal_news() {
        return total_news;
    }

    public void setTotal_news(Long total_news) {
        this.total_news = total_news;
    }

    public List<News> getData_news() {
        return data_news;
    }

    public void setData_news(List<News> data_news) {
        this.data_news = data_news;
    }

    public Long getTotal_feedback() {
        return total_feedback;
    }

    public void setTotal_feedback(Long total_feedback) {
        this.total_feedback = total_feedback;
    }

    public List<FeedBack> getData_feedback() {
        return data_feedback;
    }

    public void setData_feedback(List<FeedBack> data_feedback) {
        this.data_feedback = data_feedback;
    }

    public Long getTotal_comment() {
        return total_comment;
    }

    public void setTotal_comment(Long total_comment) {
        this.total_comment = total_comment;
    }

    public List<Comment> getData_comment() {
        return data_comment;
    }

    public void setData_comment(List<Comment> data_comment) {
        this.data_comment = data_comment;
    }

    public Long getTotal_find() {
        return total_find;
    }

    public void setTotal_find(Long total_find) {
        this.total_find = total_find;
    }

    public List<Goods> getData_find() {
        return data_find;
    }

    public void setData_find(List<Goods> data_find) {
        this.data_find = data_find;
    }

    public Long getTotal_lost() {
        return total_lost;
    }

    public void setTotal_lost(Long total_lost) {
        this.total_lost = total_lost;
    }

    public List<Goods> getData_lost() {
        return data_lost;
    }

    public void setData_lost(List<Goods> data_lost) {
        this.data_lost = data_lost;
    }

    public Integer getTotal_usercomment() {
        return total_usercomment;
    }

    public void setTotal_usercomment(Integer total_usercomment) {
        this.total_usercomment = total_usercomment;
    }

    public List<Comment> getData_usercomment() {
        return data_usercomment;
    }

    public void setData_usercomment(List<Comment> data_usercomment) {
        this.data_usercomment = data_usercomment;
    }
}
