package com.czx.easydemo.service;

import com.czx.easydemo.model.Order;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    @Transactional
    int createOrder(Order order) throws InterruptedException;

    Order findOrder(Long id);
}
