package com.janitha.videoenhancer.client.app.controller;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import com.janitha.videoenhancer.client.app.config.EnvConfig;


public class BaseController {

    @Autowired
    private EnvConfig envConfig;

    public void setLogIdentifier(HttpServletRequest request) {

        String logIdentifier = request.getHeader(envConfig.getLogIdentifierKey());
        if (logIdentifier != null) {
            MDC.put(envConfig.getLogIdentifierKey(), logIdentifier);
        } else {
            MDC.put(envConfig.getLogIdentifierKey(), UUID.randomUUID().toString());
        }
    }
}

