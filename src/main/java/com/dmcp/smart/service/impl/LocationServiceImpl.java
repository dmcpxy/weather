package com.dmcp.smart.service.impl;

import com.dmcp.smart.common.http.HttpUtils;
import com.dmcp.smart.dao.LocationDao;
import com.dmcp.smart.entity.Location;
import com.dmcp.smart.service.LocationService;
import com.dmcp.smart.service.URLConstants;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * $Id: LocationServiceImpl.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/2 23:09
 */
public class LocationServiceImpl implements LocationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);
    private int httpRequestTimeout = 30000;
    @Autowired
    private LocationDao cityDao;
    private int pageSize = 10;

    public void setHttpRequestTimeout(int httpRequestTimeout) {
        if (httpRequestTimeout > 3000) {
            this.httpRequestTimeout = httpRequestTimeout;
        }
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int addCityInfo(Location info) {
        String cityName = info.getName();
        Location cityByName = findCityByName(cityName);
        if (null != cityByName) {
            info.setId(cityByName.getId());
            return updateCityInfo(info);
        }

        return cityDao.add(info);
    }

    @Override
    public void deleteCityInfo(Integer cityId) {
        cityDao.delete(cityId);
    }

    @Override
    public Location findCityById(Integer cityId) {
        return cityDao.findById(cityId);
    }

    @Override
    public Location findCityByName(String name) {
        return cityDao.findByName(name);
    }

    @Override
    public List<Location> listAll() {
        return cityDao.list();
    }

    @Override
    public JSONObject searchCity(String name) {
        String url = String.format(URLConstants.FIND_CITY_URL_TEMPLATE, name, pageSize);
        String response = HttpUtils.get(url, null, httpRequestTimeout);
        LOGGER.debug("search city result: {}", response);

        return JSONObject.fromObject(response);
    }

    @Override
    public int updateCityInfo(Location info) {
        return cityDao.update(info);
    }
}
