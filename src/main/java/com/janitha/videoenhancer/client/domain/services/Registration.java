package com.janitha.videoenhancer.client.domain.services;

import com.janitha.videoenhancer.client.external.models.registerRequest;
import com.janitha.videoenhancer.client.external.models.unregisterCloudletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Registration {

    public Registration() {
    }

    @Value("${micro_sercice.port}")
    private String port;

    @Value("${micro_sercice.address}")
    private String address;

    @Value("${server.port}")
    private String myport;

    @Value("${server.address}")
    private String myaddress;

    @PostConstruct
    private void postRegistration()
    {
        String uri="http://" + address + ":" + port + "/registerPlugin/";
        RestTemplate restTemplate = new RestTemplate();
        registerRequest tmp = new registerRequest(myaddress, myport, "cloudlet_zero_dawn", "SL");

        ResponseEntity<String> result = restTemplate.postForEntity(uri, tmp, String.class);
    }

    @PreDestroy
    private void unRegistration()
    {
        String uri="http://" + address + ":" + port + "/unregisterPlugin/";
        unregisterCloudletRequest tmp = new unregisterCloudletRequest(myaddress, myport);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, tmp, String.class);
    }

}
