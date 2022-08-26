package com.janitha.videoenhancer.client.domain.mdbspringboot.model;


import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.ServerSocket;


public class cloudletPluginArguments {

    @Getter
    @Setter
    public String port = "8080";

    @Getter
    @Setter
    public String videoURL;

    public int getAnAvailablePort() throws IOException {
        int[] ports = new int[] { 8081,3843, 4584, 4843 };
        for (int port : ports) {
            try {
                new ServerSocket(port).close();
                return port;
            } catch (IOException ex) {
                continue; // try next port
            }
        }

        // if the program gets here, no port in the range was found
        throw new IOException("no free port found");
    }
    public cloudletPluginArguments(String port, String videoURL) throws IOException {
        if(port.equals("0")){
            this.port = String.valueOf(getAnAvailablePort());
        }else{
            this.port = port;
        }
        this.videoURL = videoURL;
    }
}
