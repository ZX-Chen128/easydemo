package com.czx.easydemo.controller;

import com.czx.easydemo.common.api.CommonResult;
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
@RequestMapping("/redis")
@Api(tags = "RedisController", description = "Redis测试")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @ApiOperation("测试简单缓存")
    @RequestMapping(value = "/simpleTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> simpleTest() {
        String s = "abc";
        String key = "I'm key";
        redisService.set(key,s);
        String cache = (String) redisService.get(key);
        return CommonResult.success(cache);
    }

}
