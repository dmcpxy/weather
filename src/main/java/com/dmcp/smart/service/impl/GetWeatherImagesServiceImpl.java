package com.dmcp.smart.service.impl;

import com.dmcp.smart.common.http.HttpUtils;
import com.dmcp.smart.entity.Location;
import com.dmcp.smart.service.GetWeatherImagesService;
import com.dmcp.smart.service.LocationService;
import com.dmcp.smart.service.URLConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * $Id: GetWeatherImagesServiceImpl.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/2 18:29
 */

public class GetWeatherImagesServiceImpl implements GetWeatherImagesService {
    private final Logger logger = LoggerFactory.getLogger(GetWeatherImagesServiceImpl.class);
    private int httpRequestTimeout = 30000;
    private String downloadPath;
    private boolean downloadOncePerDay = false;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;


    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public void setDownloadOncePerDay(boolean downloadOncePerDay) {
        this.downloadOncePerDay = downloadOncePerDay;
    }

    public void setHttpRequestTimeout(int httpRequestTimeout) {
        if (httpRequestTimeout > 3000) {
            this.httpRequestTimeout = httpRequestTimeout;
        }
    }

    @Override
    public void init() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String format1 = format.format(now);
        String eightClock = format1.concat("080000");
        try {
            Date eightClockDate = format2.parse(eightClock);

            if (now.after(eightClockDate)) {
                logger.info("now is after eight clock, download today's images.");
                taskExecutor.submit(new Runnable() {
                    @Override
                    public void run() {
                        download();
                    }
                });
            } else {
                logger.info("now is before eight clock");
            }
        } catch (Exception e) {
            logger.error("init failed", e);
        }
    }

    @Override
    public void download() {
        logger.info("start batch download images");
        long start = System.currentTimeMillis();
        List<Location> locations = locationService.listAll();
        if (CollectionUtils.isEmpty(locations)) {
            logger.warn("no location's weather image to download.");
            return;
        }

        logger.info("downloading images, location list[size={}] is: {}", locations.size(), locations);

        for (Location info : locations) {
            downloadOne(info);
        }

        long end = System.currentTimeMillis();
        logger.info("finished batch download, total usedTime={}s", (end - start)/1000);
    }

    @Override
    public void downloadOne(Location info) {
        if (StringUtils.isBlank(info.getUrl())) {
            logger.error("location: {} url is blank!", info.getName());
            return;
        }
        String pageUrl = String.format(URLConstants.IMAGE_PAGE_URL_TEMPLATE, info.getUrl());

        String pageContent = HttpUtils.get(pageUrl, null, httpRequestTimeout);

        String imageDownloadUrl = getImageDownloadUrl(pageContent);
        logger.info("original imageDownloadUrl is: {}", imageDownloadUrl);

        imageDownloadUrl = imageDownloadUrl.replaceAll("&amp;", "&");

        if (imageDownloadUrl.startsWith("//")) {
            imageDownloadUrl = "https:" + imageDownloadUrl;
        }
        logger.info("\n\n>>>imageDownloadUrl is: {}", imageDownloadUrl);

        downloadImage(imageDownloadUrl, info.getName());
    }

    private String getImageDownloadUrl(String pageContent) {
        int chart_download = pageContent.indexOf("chart_download");
        int start = pageContent.indexOf("href=\"", chart_download);
        int end = pageContent.indexOf("\"", start + 10);

        return pageContent.substring(start + 6, end);
    }

    protected void downloadImage(String url, String location) {
        File targetFile = getTargetFile(location);

        if (targetFile.exists() && downloadOncePerDay) {
            logger.info("today's images exists, not downloading...");
            return;
        } else {
            if (targetFile.exists()) {
                //rename old file
                long lastModified = targetFile.lastModified();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String createTimeStr = format.format(new Date(lastModified));
                String newFileName = targetFile.getName().replaceAll("\\d{8}", createTimeStr);
                try {
                    FileUtils.moveFile(targetFile, new File(targetFile.getParent(), newFileName));
                } catch (Exception e) {
                    logger.error("move file failed.", e);
                }
            }
        }

        long startTime = System.currentTimeMillis();
        byte[] asBinary = HttpUtils.getAsBinary(url, null, httpRequestTimeout);
        logger.info("image size={}, download usedTime={}ms", asBinary.length, (System.currentTimeMillis() - startTime));
        try {
            FileUtils.writeByteArrayToFile(targetFile, asBinary);
            logger.debug("write to file: {} successfully.", targetFile.getAbsolutePath());
        } catch (IOException e) {
            logger.error("downloadImage: {} failed", url, e);
        }
    }

    private File getTargetFile(String location) {
        String targetPath = downloadPath + File.separator + location;
        File targetFileDir = new File(targetPath);
        if (!targetFileDir.isDirectory() || !targetFileDir.exists()) {
            try {
                FileUtils.forceMkdir(targetFileDir);
            } catch (IOException e) {
                logger.error("mkdir: {} failed", targetPath, e);
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String today = format.format(new Date());
        String fileName = String.format("%s_%s.png", location, today);

        return new File(targetPath, fileName);
    }


}
