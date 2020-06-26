package com.czx.easydemo.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class IndexController {

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/deduct_stock")
    public String deductStock() throws InterruptedException {

        String clientID = UUID.randomUUID().toString();
        RLock redissonLock = redisson.getLock("lockey");

        try {
            //Boolean result = stringRedisTemplate.opsForValue().setIfAbsent("lockey", "czx666"); //jedis.setnx("stock","czx666);
            //stringRedisTemplate.expire("lockey",10, TimeUnit.SECONDS);

            /*Boolean result = stringRedisTemplate.opsForValue().setIfAbsent("lockey", clientID, 10, TimeUnit.SECONDS);

            if(!result){
                return "error";
            }

             */


            //获得线程加锁成功，每隔10s检查是否还持有锁，如果持有则延长锁的时间
            //如果没有获得线程，则while循环，一直尝试加锁（自旋）
            redissonLock.lock(30,TimeUnit.SECONDS);

            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); //jedis.get("stock");
            if(stock > 0){
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock",realStock+""); //jedis.set(key,value);
                System.out.println("扣减成功，剩余库存:" + realStock + "");
            } else {
                System.out.println("扣减失败，库存不足");
            }
        }finally {
            redissonLock.unlock();
            /*
            if(clientID.equals(stringRedisTemplate.opsForValue().get("lockey"))) {
                //释放锁
                stringRedisTemplate.delete("lockey");
            }

             */
        }

        return "end";
    }
}
