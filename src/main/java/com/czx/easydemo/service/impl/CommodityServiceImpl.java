package com.czx.easydemo.service.impl;

import com.czx.easydemo.mapper.CommodityMapper;
import com.czx.easydemo.model.Commodity;
import com.czx.easydemo.model.CommodityExample;
import com.czx.easydemo.service.CommodityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public int createCommodity(Commodity commodityAdminParam) {
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityAdminParam, commodity);
        //查询是否有相同用户名的商品
        CommodityExample commodityExample = new CommodityExample();
        commodityExample.createCriteria().andNameEqualTo(commodity.getName());
        List<Commodity> commodityList = commodityMapper.selectByExample(commodityExample);

        return commodityList.size() > 0 ? 0 : commodityMapper.insert(commodityAdminParam);
    }

    @Override
    public int deleteCommodity(Long id) {
        return commodityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int changeCommodity(Commodity commodity) {
        return commodityMapper.updateByPrimaryKey(commodity);
    }

    @Override
    public Commodity findCommodity(Long id) {
        return commodityMapper.selectByPrimaryKey(id);
    }
}