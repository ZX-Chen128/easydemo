package com.czx.easydemo.redis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/deduct_stock")
    public String deductStock() throws InterruptedException {
        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); //jedis.get("stock");
        if(stock > 0){
            int realStock = stock - 1;
            stringRedisTemplate.opsForValue().set("stock",realStock+""); //jedis.set(key,value);
            System.out.println("扣减成功，剩余库存:" + realStock + "");
        } else {
            System.out.println("扣减失败，库存不足");
        }
        return "end";
    }
}
