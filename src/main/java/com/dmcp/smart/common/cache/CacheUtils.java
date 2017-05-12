package com.dmcp.smart.common.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * $Id: CacheUtils.java
 * <p>
 * Copyright(c) 1995-2016 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/3/16 15:39
 */
public class CacheUtils {

    /**
     * 从缓存中获取对象
     *
     * @param cacheManager cache manager
     * @param cacheName    cache policy name
     * @param key          cache's key
     * @param type         class type
     * @param <T>          obj type
     * @return obj
     */
    public static <T> T getCache(CacheManager cacheManager, String cacheName, String key, Class<T> type) {
        Cache cache = cacheManager.getCache(cacheName);

        return cache != null ? cache.get(key, type) : null;
    }

    /**
     * 从缓存中获取对象
     *
     * @param cacheManager cache manager
     * @param cacheName    cache policy name
     * @param key          cache's key
     * @return obj
     */
    public static Object getCache(CacheManager cacheManager, String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);

        return cache != null ? cache.get(key) : null;
    }

    /**
     * 删除某个缓存
     *
     * @param cacheManager cache manager
     * @param cacheName    cache policy name
     * @param key          cache's key
     */
    public static void deleteCache(CacheManager cacheManager, String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);

        if (null != cache) {
            cache.evict(key);
        }
    }

    /**
     * 写入缓存
     *
     * @param cacheManager cache manager
     * @param cacheName    cache policy name
     * @param key          cache's key
     * @param value        要缓存的对象
     */
    public static void putCache(CacheManager cacheManager, String cacheName, String key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }
}
