package com.gyf.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class TestRedis {
    public static void main(String[] args) {
        //1.连接redis
        Jedis jedis=new Jedis("192.168.195.134");
        //2.测试是否连接通过
        System.out.println(jedis.ping());

        //3.String
        jedis.set("郭云飞","陈竟硕");
        System.out.println(jedis.get("郭云飞"));
        System.out.println(jedis.get("13 "));

        //4.list
        jedis.lpush("list1","aaa"); //list1{1,3,4}
        jedis.lpush("list1","bbb");
        jedis.lpush("list1","ccc");
        //jedis.lrange(key,start,stop);
        List<String> list1=jedis.lrange("list1",0,2);

        System.out.println(list1.get(1));
    }
}
