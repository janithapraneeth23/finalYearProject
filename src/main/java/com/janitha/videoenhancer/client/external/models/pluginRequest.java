package com.janitha.videoenhancer.client.external.models;

public class pluginRequest {
    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public pluginRequest(String pluginName, String URL) {
        this.pluginName = pluginName;
        this.URL = URL;
    }

    String pluginName;
    String URL;



}
