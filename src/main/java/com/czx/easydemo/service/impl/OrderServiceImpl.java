package com.czx.easydemo.service.impl;

import com.czx.easydemo.mapper.CommodityMapper;
import com.czx.easydemo.mapper.OrderMapper;
import com.czx.easydemo.mapper.UserMapper;
import com.czx.easydemo.model.Commodity;
import com.czx.easydemo.model.Order;
import com.czx.easydemo.model.User;
import com.czx.easydemo.service.OrderService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int createOrder(Order order) throws InterruptedException {

        RLock redissonLock = redisson.getLock("lockey");

        try {

            //获得线程加锁成功，每隔10s检查是否还持有锁，如果持有则延长锁的时间
            //如果没有获得线程，则while循环，一直尝试加锁（自旋）
            redissonLock.lock(30, TimeUnit.SECONDS);
            int orderedstock = order.getNumber();
            int remainedstock = commodityMapper.selectByPrimaryKey(order.getCommodityid()).getStock();
            int singleprice = commodityMapper.selectByPrimaryKey(order.getCommodityid()).getPrice();
            int totalprice = orderedstock*singleprice;
            int userdeposit = userMapper.selectByPrimaryKey(order.getBuyerid()).getDeposit();
            if(orderedstock > remainedstock){
                return 0;
            }
            if(totalprice > userdeposit){
                return 0;
            }

            Commodity commodity = commodityMapper.selectByPrimaryKey(order.getCommodityid());
            commodity.setStock(remainedstock - orderedstock);
            commodityMapper.updateByPrimaryKey(commodity);
            User user = userMapper.selectByPrimaryKey(order.getBuyerid());
            user.setDeposit(userdeposit - totalprice);
            userMapper.updateByPrimaryKey(user);
            return orderMapper.insert(order);

        } finally {
            redissonLock.unlock();
        }

    }

    @Override
    public Order findOrder(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}
