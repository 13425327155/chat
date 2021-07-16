package com.example.cchat.controller;

import com.example.cchat.entity.News;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping("/findAll")
    public List<News> findAll(){
        return newsService.findAllNews();
    }

    @GetMapping("/findByPage/{page}/{size}")
    public RespPageBean findByPage(@PathVariable("page") Integer page,
                                   @PathVariable("size") Integer size) {
        return newsService.findByPage(page, size);
    }

    @GetMapping("/findById/{id}")
    public News findById(@PathVariable("id") int id){
        return newsService.findById(id);
    }

    @GetMapping("/findByName/{n_title}")
    public List<News> findByTitle(@PathVariable("n_title") String n_title){
        return newsService.findByTitle(n_title);
    }

    @PutMapping("/alt")
    public String altHouse(@RequestBody News news) {
        int altResult = newsService.altNews(news);
        if (altResult == 1) {
            return "alt success!";
        } else return "alt error!";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        newsService.deleteById(id);
        if (newsService.findById(id) == null){
            return "delete success!";
        }else return "delete error!";
    }
}
