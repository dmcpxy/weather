package com.dmcp.smart;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * $Id: ControllerTestSupport.java
 * <p/>
 * Copyright(c) 1995-2014 by Asiainfo(China)
 * All rights reserved.
 *
 * @author Jacob Liu <liuxy2@asiainfo.com>
 *         14/11/13 22:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springmvc-servlet.xml" })
public class ControllerTestSupport {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    /**
     * add comment for setUp
     */
    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }
}