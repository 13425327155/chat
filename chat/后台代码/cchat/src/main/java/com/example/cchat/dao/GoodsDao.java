package com.example.cchat.dao;

import com.example.cchat.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {
    //寻物
    List<Goods> findFindGoods(@Param("page") Integer page, @Param("size") Integer size);
    //失物
    List<Goods> findLostGoods(@Param("page") Integer page, @Param("size") Integer size);

    //分页
    Long getFindTotal();
    Long getLostTotal();

    Goods findById(@Param("id") int id);
    //删除物品信息
    void deleteById(@Param("id") int id);
}
