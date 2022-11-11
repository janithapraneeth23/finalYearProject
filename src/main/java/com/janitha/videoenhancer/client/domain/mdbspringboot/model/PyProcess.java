package com.janitha.videoenhancer.client.domain.mdbspringboot.model;

import lombok.Getter;
import lombok.Setter;

public class PyProcess {

    public PyProcess(Process process, cloudletPluginArguments pluginArguments, String plugInName) {
        this.process = process;
        this.pluginArguments = pluginArguments;
        this.plugInName = plugInName;
    }

    public String getURL()
    {
        return pluginArguments.getVideoURL();
    }

    public String getPort()
    {
        return pluginArguments.getPort();
    }

    @Getter
    @Setter
    Process process;
    @Getter
    @Setter
    cloudletPluginArguments pluginArguments;

    @Getter
    @Setter
    String plugInName;
}
