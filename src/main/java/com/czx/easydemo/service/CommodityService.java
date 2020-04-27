package com.czx.easydemo.service;

import com.czx.easydemo.model.Commodity;
import org.springframework.transaction.annotation.Transactional;

public interface CommodityService {

    @Transactional
    int createCommodity(Commodity commodity);

    int deleteCommodity(Long id);

    int changeCommodity(Commodity commodity);

    Commodity findCommodity(Long id);

}
