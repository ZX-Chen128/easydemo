package com.czx.easydemo.controller;


import com.czx.easydemo.common.api.CommonResult;
import com.czx.easydemo.model.Commodity;
import com.czx.easydemo.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/commodity")
@Api(tags = "CommodityController", description = "商品管理")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @ApiOperation("创建商品")
    @RequestMapping(value = "/creatCommodity", method = RequestMethod.POST)
    public CommonResult<Integer> createCommodity(Commodity commodity){
        int createCommodity = commodityService.createCommodity(commodity);
        if(createCommodity==0){
            return CommonResult.failed();
        } else{
            return CommonResult.success(createCommodity);
        }
    }

    @ApiOperation("删除商品")
    @RequestMapping(value = "/deleteCommodity", method = RequestMethod.POST)
    public CommonResult<Integer> deleteCommodity(Long id){
        int deleteCommodity = commodityService.deleteCommodity(id);
        if(deleteCommodity==0){
            return CommonResult.failed();
        } else {
            return CommonResult.success(deleteCommodity);
        }
    }

    @ApiOperation("修改商品")
    @RequestMapping(value = "/changeCommodity", method = RequestMethod.POST)
    public CommonResult<Integer> changeCommodity(Commodity commodity){
        int changeCommodity = commodityService.changeCommodity(commodity);
        if(changeCommodity==0){
            return CommonResult.failed();
        } else{
            return CommonResult.success(changeCommodity);
        }
    }


    @ApiOperation("查找商品")
    @RequestMapping(value = "/findCommodity", method = RequestMethod.POST)
    public CommonResult<Commodity> findCommodity(Long id){
        Commodity commodity = commodityService.findCommodity(id);
        return CommonResult.success(commodity);
    }

}
