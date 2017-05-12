package com.dmcp.smart.service;

/**
 * $Id: URLConstants.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/3 10:43
 */
public class URLConstants {
    public static final String IMAGE_DOWNLOAD_URL_TEMPLATE = "https://my.meteoblue.com/visimage/meteogram_web_hd?look=CELSIUS%2CKILOMETER_PER_HOUR%2CMILIMETER&apikey=5838a18e295d&cache=no&city=Guangzhou&iso2=cn&lat=23.116699&lon=113.250000&asl=15&tz=Asia%2FShanghai&lang=en&ts=1493778939&sig=e2ce315ae806c6dd7e1b31b15defe351";

    public static final String IMAGE_PAGE_URL_TEMPLATE = "https://www.meteoblue.com/en/weather/forecast/meteogramfive/%s";
    public static final String IMAGE_PAGE_URL_DEMO = "https://www.meteoblue.com/en/weather/forecast/meteogramfive/guangzhou_china_1809858";
    public static final String FIND_CITY_URL_TEMPLATE = "https://www.meteoblue.com/en/server/search/query3?query=%s&orderBy=&itemsPerPage=%d&page=1";
    public static final String FIND_CITY_URL_DEMO = "https://www.meteoblue.com/en/server/search/query3?query=zhongshan&orderBy=&itemsPerPage=10&page=1";
}
