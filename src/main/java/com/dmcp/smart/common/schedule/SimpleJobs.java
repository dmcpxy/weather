package com.dmcp.smart.common.schedule;

import com.dmcp.smart.common.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * $Id: Jobs.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights responseerved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/3/22 09:51
 */
//@Configuration
//@EnableScheduling
public class SimpleJobs {
    private final Logger logger = LoggerFactory.getLogger(SimpleJobs.class);

    //@Scheduled(cron = "0/30 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        logger.info(">>>>>>>>>>>>> scheduled ..., class: {} ", getClass());
        String url = "https://www.meteoblue.com/en/server/search/query3";
        String data = "query=zhongshan&orderBy=&itemsPerPage=10&page=1";

        String response = HttpUtils.get(url, data, 10 * 1000);

        logger.info("response: {}", response);
    }

}
