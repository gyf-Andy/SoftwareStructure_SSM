package com.gyf.service.impl;

import com.gyf.mapper.GoodsMapper;
import com.gyf.pojo.Goods;
import com.gyf.result.ResultObject;
import com.gyf.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    public ResultObject addGoods(Goods goods) {
        if (goodsMapper.addGoods(goods)>0){
            return new ResultObject(200,"上传成功",null);
        }else{
            return new ResultObject(201,"上传失败",null);
        }
    }
}
