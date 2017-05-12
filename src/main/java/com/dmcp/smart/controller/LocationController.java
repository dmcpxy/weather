package com.dmcp.smart.controller;

import com.dmcp.smart.entity.Location;
import com.dmcp.smart.service.LocationService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * $Id: CityController.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/3 14:08
 */
@Controller()
@RequestMapping("/location")
public class LocationController {
    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
    @Autowired
    private LocationService locationService;

    @RequestMapping("/search")
    @ResponseBody
    public Object search(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }

        JSONObject jsonObject = locationService.searchCity(name);
        if (null != jsonObject) {
            return jsonObject.get("results");
        }
        return null;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Location> list() {
        return locationService.listAll();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            locationService.deleteCityInfo(id);
            result.put("result", 0);
        } catch (Exception e) {
            result.put("result", 1);
            result.put("msg", e.getMessage());
            logger.error("delete location failed", e);
        }

        return result;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(@ModelAttribute("location") Location location, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            int rows = locationService.addCityInfo(location);
            result.put("result", rows == 1 ? 0 : 1);
        } catch (Exception e) {
            result.put("result", 1);
            result.put("msg", e.getMessage());

            logger.error("add location failed", e);
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@ModelAttribute("location") Location location, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            int rows = locationService.updateCityInfo(location);
            result.put("result", rows == 1 ? 0 : 1);
        } catch (Exception e) {
            result.put("result", 1);
            result.put("msg", e.getMessage());
            logger.error("update location failed", e);
        }
        return result;
    }
}
