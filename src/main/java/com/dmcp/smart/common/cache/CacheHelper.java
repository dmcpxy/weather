package com.dmcp.smart.common.cache;


import net.sf.ehcache.Ehcache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

/**
 * $Id: CacheHelper.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/3/21 16:36
 */
@Component
public class CacheHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(CacheHelper.class);
    @Autowired
    private CacheManager cacheManager;


    /**
     * 从缓存中获取对象
     *
     * @param cacheName cache policy name
     * @param key       cache's key
     * @param type      class type
     * @param <T>       obj type
     * @return obj
     */
    public <T> T getCache(String cacheName, String key, Class<T> type) {
        Cache cache = cacheManager.getCache(cacheName);

        if (null == cache) {
            throw new CacheException(String.format("cache %s not exists", cacheName));
        }

        return cache.get(key, type);
    }

    /**
     * 从缓存中获取对象
     *
     * @param cacheName cache policy name
     * @param key       cache's key
     * @return obj
     */
    public Object getCache(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);

        if (null == cache) {
            throw new CacheException(String.format("cache %s not exists", cacheName));
        }

        return cache.get(key);
    }

    /**
     * 删除某个缓存
     *
     * @param cacheName cache policy name
     * @param key       cache's key
     */
    public void deleteCache(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);

        if (null != cache) {
            cache.evict(key);
        }
    }

    /**
     * 写入缓存
     *
     * @param cacheName cache policy name
     * @param key       cache's key
     * @param value     要缓存的对象
     */
    public void putCache(String cacheName, String key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);

        if (null == cache) {
            throw new CacheException(String.format("cache %s not exists", cacheName));
        }

        cache.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public Collection getKeys(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (null == cache) {
            LOGGER.debug(String.format("cache %s not exists", cacheName));
            return Collections.emptyList();
        }

        Object nativeCache = cache.getNativeCache();
        if (nativeCache instanceof Ehcache) {
            Ehcache ehcache = (Ehcache) nativeCache;
            return ehcache.getKeys();
        }

        throw new CacheException(String.format("not supported cache type[%s], must be ehcache or redis cache.", nativeCache.getClass()));
    }
}
