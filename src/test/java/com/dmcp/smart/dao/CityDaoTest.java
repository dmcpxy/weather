package com.dmcp.smart.dao;

import com.dmcp.smart.TestSupport;
import com.dmcp.smart.entity.Location;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * $Id: CityDaoTest.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/3 10:28
 */
public class CityDaoTest extends TestSupport {
    @Autowired
    private LocationDao cityDao;

    @Test
    public void add() throws Exception {

    }

    @Test
    public void findById() throws Exception {
        Location byId = cityDao.findById(1);

        System.out.println(byId);
    }

    @Test
    public void findByName() throws Exception {
        Location byId = cityDao.findByName("Guangzhou");

        System.out.println(byId);
    }

    @Test
    public void list() throws Exception {
        List<Location> list = cityDao.list();
        System.out.println("list.size=" + list.size());
        for (Location info : list) {
            System.out.println(info);
        }
    }

}