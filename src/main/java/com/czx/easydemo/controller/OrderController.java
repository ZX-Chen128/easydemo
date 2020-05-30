package com.czx.easydemo.controller;

import com.czx.easydemo.common.api.CommonResult;
import com.czx.easydemo.model.Order;
import com.czx.easydemo.service.OrderService;
import com.czx.easydemo.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/order")
@Api(tags = "OrderController", description = "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisService redisService;

    @ApiOperation("创建订单")
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public CommonResult<Integer> createOrder(Order order) throws InterruptedException {
        int createOrder = orderService.createOrder(order);
        if (createOrder == 0) {
            return CommonResult.failed();
        } else {
            String orderid = "order"+order.getOrderid();
            redisService.set(orderid,order);
            return CommonResult.success(createOrder);
        }
    }

    @ApiOperation("查找订单")
    @RequestMapping(value = "/findOrder", method = RequestMethod.POST)
    public CommonResult<Order> findOrder(Long id) {
        Order order;
        String orderid = "order"+id;
        order = (Order) redisService.get(orderid);
        if(order == null) {
            order = orderService.findOrder(id);
            redisService.set(orderid,order);
        }
        return CommonResult.success(order);
    }

}
