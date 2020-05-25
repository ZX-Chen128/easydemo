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

    @ApiOperation("测试简单缓存提交")
    @RequestMapping(value = "/simpleTestSubmit", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> simpleTestSubmit(String key , String s) {
        redisService.set(key,s);
        return CommonResult.success("succeed");
    }

    @ApiOperation("测试简单缓存读取")
    @RequestMapping(value = "/simpleTestGet", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> simpleTestGet(String key) {
        String cache = (String) redisService.get(key);
        if(cache!=null) {
            return CommonResult.success(cache);
        } else {
            return CommonResult.failed();
        }
    }

}
