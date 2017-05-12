package com.dmcp.smart.common.http;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;

/**
 * $Id: HttpUtils.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/1 21:38
 */
public class HttpUtils {
    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);


    public static byte[] request(HttpMethod method, String url, String data, int timeout) {
        try {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(timeout);
            factory.setReadTimeout(timeout);
            ClientHttpRequest request = factory.createRequest(new URI(url), method);
            if (StringUtils.isNotBlank(data)) {
                request.getBody().write(data.getBytes());
            }

            ClientHttpResponse response = request.execute();

            HttpStatus statusCode = response.getStatusCode();
            if (logger.isDebugEnabled()) {
                logger.debug("response status is: {}", statusCode);
            }

            if (statusCode.is2xxSuccessful()) {
                InputStream is = response.getBody();

                ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int readLen;

                while ((readLen = is.read(buffer)) > 0) {
                    byteArrayInputStream.write(buffer, 0, readLen);
                }

                return byteArrayInputStream.toByteArray();
            }

        } catch (Exception e) {
            logger.error("open url:{} failed", url, e);
        }
        return new byte[0];
    }

    public static String post(String url, String data, int timeout) {
        byte[] request = request(HttpMethod.POST, url, data, timeout);
        return new String(request);
    }

    public static String get(String url, String data, int timeout) {
        byte[] request = request(HttpMethod.GET, url, data, timeout);
        return new String(request);
    }

    public static byte[] getAsBinary(String url, String data, int timeout) {
        return request(HttpMethod.GET, url, data, timeout);
    }
}
