package com.example.cchat.service;

import com.example.cchat.entity.Goods;
import com.example.cchat.entity.RespPageBean;

public interface GoodsService {
    //寻物
    Long getFindTotal();
    RespPageBean findFindGoods(Integer page, Integer size);

    //失物
    Long getLostTotal();
    RespPageBean findLostGoods(Integer page, Integer size);

    Goods findById(int id);

    void deleteById(int id);

    boolean addGoods(Goods goods);
}
