package com.example.cchat.controller;

import com.example.cchat.entity.Goods;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/findFindGoods/{page}/{size}")
    public RespPageBean findFindGoods(@PathVariable("page") Integer page,
                                   @PathVariable("size") Integer size) {
        return goodsService.findFindGoods(page, size);
    }

    @GetMapping("/findLostGoods/{page}/{size}")
    public RespPageBean findLostGoods(@PathVariable("page") Integer page,
                                      @PathVariable("size") Integer size) {
        return goodsService.findLostGoods(page, size);
    }

    @GetMapping("/findById/{id}")
    public Goods findById(@PathVariable("id") int id){
        return goodsService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        goodsService.deleteById(id);
        if (goodsService.findById(id) == null){
            return "delete success!";
        }else return "delete error!";
    }

}
