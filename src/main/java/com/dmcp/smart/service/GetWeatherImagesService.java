package com.dmcp.smart.service;

import com.dmcp.smart.entity.Location;

/**
 * $Id: GetWeatherImagesServiceImpl.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/2 18:29
 */
public interface GetWeatherImagesService {

    void downloadOne(Location info);

    void download();

    void init();
}
