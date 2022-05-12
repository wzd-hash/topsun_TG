package com.topsun.service.impl;

import com.topsun.entity.Location;
import com.topsun.entity.Roules;
import com.topsun.mapper.LocationMapper;
import com.topsun.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description 区域实现类
 * @author: wzd
 * @create: 2021-11-25 16:28
 * @Version 1.0
 **/
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationMapper locationMapper;


    @Override
    public List<Location> listLocationByType(Map<String, Object> map) {
        return locationMapper.listLocationByType(map);
    }

    @Override
    public Integer insertLocation(Location location) {
        return locationMapper.insertLocation(location);
    }

    @Override
    public Integer updateLocationByType(Location location) {
        return locationMapper.updateLocationByType(location);
    }

    @Override
    public Integer delLocation(Integer id) {
        return locationMapper.delLocation(id);
    }

    @Override
    public List<Roules> getCompanyID(Map<String, Object> map) {
        return locationMapper.getCompanyID(map);
    }
}
