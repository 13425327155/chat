package com.example.cchat.service.Impl;

import com.example.cchat.dao.GoodsDao;
import com.example.cchat.entity.Goods;
import com.example.cchat.entity.RespPageBean;
import com.example.cchat.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    @Override
    public Long getFindTotal() {
        return goodsDao.getFindTotal();
    }

    @Override
    public RespPageBean findFindGoods(Integer page, Integer size) {
        if(page!=null&&size!=null){
            page=(page-1)*size;
        }
        List<Goods> data=goodsDao.findFindGoods(page,size);
        Long total=goodsDao.getFindTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData_find(data);
        bean.setTotal_find(total);
        return bean;
    }

    @Override
    public Long getLostTotal() {
        return goodsDao.getLostTotal();
    }

    @Override
    public RespPageBean findLostGoods(Integer page, Integer size) {
        if(page!=null&&size!=null){
            page=(page-1)*size;
        }
        List<Goods> data=goodsDao.findLostGoods(page,size);
        Long total=goodsDao.getLostTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData_lost(data);
        bean.setTotal_lost(total);
        return bean;
    }

    @Override
    public Goods findById(int id) {
        return goodsDao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        goodsDao.deleteById(id);
    }
}
