package com.dmcp.smart.common.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * $Id: CacheTestController.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/3/15 19:43
 */

@Controller
@RequestMapping("/cache")
public class CacheHelperController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CacheHelperController.class);

    @Resource
    private CacheManager cacheManager;
    @Resource
    private CacheHelper cacheHelper;


    @RequestMapping("/clear")
    @ResponseBody
    public String clear(@RequestParam(name = "cache") String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);

        if (cache == null) {
            return String.format("cache %s not exists.", cacheName);
        }

        cache.clear();

        return "OK";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteCache(@RequestParam(name = "cache") String cacheName,
                              @RequestParam(name = "key") String cacheKey) {
        Cache cache = cacheManager.getCache(cacheName);

        if (cache == null) {
            return String.format("cache %s not exists.", cacheName);
        }

        cache.evict(cacheKey);

        return "OK";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Collection> caches(@RequestParam(name = "cache", required = false) String cacheName) {
        Map<String, Collection> caches = new HashMap<>();

        if (StringUtils.isNotBlank(cacheName)) {
            caches.put(cacheName, cacheHelper.getKeys(cacheName));

            return caches;
        }

        Collection<String> cacheNames = cacheManager.getCacheNames();
        for (String cacheType : cacheNames) {
            caches.put(cacheType, cacheHelper.getKeys(cacheType));
        }

        return caches;
    }
}
