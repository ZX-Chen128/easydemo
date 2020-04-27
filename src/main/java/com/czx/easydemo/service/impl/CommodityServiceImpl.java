package com.czx.easydemo.service.impl;

import com.czx.easydemo.mapper.CommodityMapper;
import com.czx.easydemo.model.Commodity;
import com.czx.easydemo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public int createCommodity(Commodity commodityAdminParam) {
        return commodityMapper.insert(commodityAdminParam);
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