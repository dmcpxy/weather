package com.dmcp.smart.common.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * $Id: Jobs.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/3/22 09:51
 */
//@Configuration
//@EnableScheduling
public class Jobs {
    private final Logger logger = LoggerFactory.getLogger(Jobs.class);

    //@Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        logger.info(">>>>>>>>>>>>> scheduled ..., class: {} ", getClass());
    }
}
