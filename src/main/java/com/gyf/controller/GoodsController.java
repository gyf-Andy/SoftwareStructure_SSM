package com.gyf.controller;

import com.alibaba.fastjson.JSONObject;
import com.gyf.pojo.Goods;
import com.gyf.service.GoodsService;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/toAddGoodsPage.action")
    public String toAddGoodsPage(){
        return "/goods/addGoods";
    }

    @RequestMapping("/addGoods.action")
    public String addGoods(String g_name,
                                 @RequestParam("g_img")MultipartFile g_img,
                                 HttpServletRequest request ) throws IOException, MyException {
        //1.加载配置文件
        ClientGlobal.init("C:\\Users\\22413\\IdeaProjects\\SoftwareStructure_SSM\\src\\main\\resources\\fastdfs_client.properties");
        //2.创建管理端对象
        TrackerClient trackerClient=new TrackerClient();
        //3.通过管理端对象获取链接
        TrackerServer connection=trackerClient.getConnection();
        //4.创建储存对象
        StorageClient1 storageClient1=new StorageClient1(connection,null);

        //创建文件属性对象信息数组
        NameValuePair[] meta_list=new NameValuePair[1];
        meta_list[0] =new NameValuePair("filename",g_img.getOriginalFilename());

        //5.上传文件
        String path=storageClient1.upload_file1(g_img.getBytes() ,"jpg",meta_list);
        //System.out.println("====="+path);
        Goods goods=new Goods(g_name,"http://192.168.195.134:83//"+path);
        goodsService.addGoods(goods); //添加到数据库中
            //把该商品再添加到redis里面 set （key -g_id,value -(商品信息-对象)）
            //set(g_id,"user-json字符串")

            Integer g_id=goods.getG_id();

            //把goods对象转换成Json字符串
            String goodsJson=JSONObject.toJSONString(goods);

            //把goods保存到redis

            Jedis jedis=new Jedis("192.168.195.134");  //连接redis
            jedis.set("goods_"+g_id,goodsJson);

        request.setAttribute("goods",goods);
        return "/goods/showGoods";
    }


    @RequestMapping("/getGoodsFromRedis.action")
    public String getGoodsFromRedis(Integer g_id){

        //去redis里面查
        Jedis jedis=new Jedis("192.168.195.134");

        String key="goods_"+g_id;
        String value=jedis.get(key);
        Goods goods=null;

        if (value!=null){
            //把从redis里面获取到的字符串转义成Goods对象
            goods=JSONObject.parseObject(value,Goods.class);

        }else{
            //如果redis里面没有想要的数据，就去mysql里面查找

        }

        System.out.println(goods);
        return "/goods/showGoods";
    }

}
