package com.topsun.service;

import com.topsun.entity.Location;
import com.topsun.entity.Roules;

import java.util.List;
import java.util.Map;

public interface LocationService {
    List<Location> listLocationByType(Map<String, Object> map);

    Integer insertLocation(Location location);

    Integer updateLocationByType(Location location);

    Integer delLocation(Integer id);

    List<Roules> getCompanyID(Map<String, Object> map);
}
