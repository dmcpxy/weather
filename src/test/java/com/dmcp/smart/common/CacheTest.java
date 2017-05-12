package com.dmcp.smart.common;

import com.dmcp.smart.common.cache.CacheConstant;
import com.dmcp.smart.common.cache.CacheHelper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * $Id: CacheTest.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/3/24 11:49
 */
public class CacheTest {
    @Resource
    private CacheHelper cacheHelper;

    @Test
    public void listCacheKey() {
        Collection keys1 = cacheHelper.getKeys(CacheConstant.DEMO_CACHE);
        System.out.println(keys1);
    }
}
