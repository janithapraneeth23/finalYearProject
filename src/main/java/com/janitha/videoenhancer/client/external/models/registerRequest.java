package com.janitha.videoenhancer.client.external.models;

public class registerRequest {
        String ip;
        String port;
        String name;
        String location;

        public registerRequest(String ip, String port, String name, String location) {
            this.ip = ip;
            this.port = port;
            this.name = name;
            this.location = location;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }


}
