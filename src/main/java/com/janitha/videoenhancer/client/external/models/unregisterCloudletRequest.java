package com.janitha.videoenhancer.client.external.models;

public class unregisterCloudletRequest {

    public unregisterCloudletRequest(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    String ip;
    String port;


}