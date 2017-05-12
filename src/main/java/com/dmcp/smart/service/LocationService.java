package com.dmcp.smart.service;

import com.dmcp.smart.entity.Location;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * $Id: LocationService.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/2 23:02
 */
public interface LocationService {
    int addCityInfo(Location info);

    int updateCityInfo(Location info);

    void deleteCityInfo(Integer cityId);

    Location findCityByName(String cityName);

    Location findCityById(Integer cityId);

    List<Location> listAll();

    JSONObject searchCity(String name);
}
