package com.czx.easydemo.controller;

import com.czx.easydemo.common.api.CommonResult;
import com.czx.easydemo.model.User;
import com.czx.easydemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/user")
@Api(tags = "UserController", description = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("创建用户")
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public CommonResult<Integer> createUser(User user) {
        int creatUser = userService.creatUser(user);
        if(creatUser==0){
            return CommonResult.failed();
        } else {
            return CommonResult.success(creatUser);
        }
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public CommonResult<Integer> deleteUser(Long id) {
        int deleteUser = userService.deletcUser(id);
        if(deleteUser==0){
            return CommonResult.failed();
        } else {
            return CommonResult.success(deleteUser);
        }
    }

    @ApiOperation("修改用户")
    @RequestMapping(value = "/changeUser", method = RequestMethod.POST)
    public CommonResult<Integer> changeUser(User user) {
        int changeUser = userService.changeUser(user);
        if(changeUser==0){
            return CommonResult.failed();
        } else {
            return CommonResult.success(changeUser);
        }
    }

    @ApiOperation("查找用户")
    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public CommonResult<User> findUser(Long id) {
        User user = userService.findUser(id);
        return CommonResult.success(user);
    }
}