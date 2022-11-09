package com.janitha.videoenhancer.client.external.models;

public class responseReqPlugin {
    public boolean isRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    boolean requestStatus;
    String host;

    public responseReqPlugin(boolean requestStatus, String host, String port) {
        this.requestStatus = requestStatus;
        this.host = host;
        this.port = port;
    }

    String port;


}
