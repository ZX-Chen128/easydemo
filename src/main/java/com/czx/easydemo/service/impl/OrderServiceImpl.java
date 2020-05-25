package com.czx.easydemo.service.impl;

import com.czx.easydemo.mapper.CommodityMapper;
import com.czx.easydemo.mapper.OrderMapper;
import com.czx.easydemo.mapper.UserMapper;
import com.czx.easydemo.model.Commodity;
import com.czx.easydemo.model.Order;
import com.czx.easydemo.model.User;
import com.czx.easydemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public int createOrder(Order order) {
        Commodity commodity = commodityMapper.selectByPrimaryKey(order.getCommodityid());
        //TODO 防止超卖
        commodity.setStock(commodity.getStock()-order.getNumber());
        commodityMapper.updateByPrimaryKey(commodity);
        User user = userMapper.selectByPrimaryKey(order.getBuyerid());
        user.setDeposit(user.getDeposit()-order.getNumber()*commodity.getPrice());
        userMapper.updateByPrimaryKey(user);
        return orderMapper.insert(order);
    }

    @Override
    public Order findOrder(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}
