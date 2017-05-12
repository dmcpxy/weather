package com.dmcp.smart.service;

import com.dmcp.smart.TestSupport;
import com.dmcp.smart.entity.Location;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * $Id: GetWeatherImagesServiceImplTest.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/3 11:31
 */
public class GetWeatherImagesServiceImplTest extends TestSupport {
    @Autowired
    private GetWeatherImagesService getWeatherImagesService;
    @Autowired
    private LocationService locationService;

    @Test
    public void downloadOne() throws Exception {
        Location info = new Location();
        info.setUrl("guangzhou_china_1809858");
        info.setName("Guangzhou");

        getWeatherImagesService.downloadOne(info);
    }


    @Test
    public void searchCity() {
        locationService.searchCity("Guangzhou");
    }

}