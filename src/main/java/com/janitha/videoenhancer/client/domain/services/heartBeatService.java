package com.janitha.videoenhancer.client.domain.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.janitha.videoenhancer.client.ServerApplication;
import com.janitha.videoenhancer.client.domain.mdbspringboot.model.CloudletInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class heartBeatService {
    private static final Logger log = (Logger) LoggerFactory.getLogger(heartBeatService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void runCurrentTime() throws UnknownHostException, JsonProcessingException {
        log.info("The time is now {}" + dateFormat.format(new Date()));
        sendHeartBeat();
    }
    @Autowired
    private ServerApplication.PubsubOutboundGateway messagingGateway;

    public void sendHeartBeat() throws UnknownHostException, JsonProcessingException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        CloudletInfo tmp = new CloudletInfo("10","local", ip,"new");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(tmp);
        messagingGateway.sendToPubsub(json);
    }

}
