package com.example.cchat.controller;

import com.example.cchat.entity.Goods;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Date;

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


    @PostMapping(value = "/addGoods")
    public boolean addGoods(
            @RequestParam("user_id") Integer user_id,
            @RequestParam("g_content") String g_content,
            @RequestParam("g_type") String g_type,
            @RequestParam("g_img") MultipartFile g_img
    ){
        //1、创建一个User对象接受请求过来的信息
        Goods goods = new Goods();
        goods.setUser_id(user_id);
        goods.setG_content(g_content);
        goods.setG_type(g_type);
        goods.setG_time(new Date());
        if (g_img != null){
            try {
//                BASE64Encoder encoder = new BASE64Encoder();
                Base64.Encoder encoder = Base64.getEncoder();
                //通过base64来转化图片
//                String data = encoder.encode(user_img.getBytes());
                String data = encoder.encodeToString(g_img.getBytes());
                //将data写入数据库
                goods.setG_img(data);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return goodsService.addGoods(goods);
    }

}
