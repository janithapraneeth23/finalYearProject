package com.janitha.videoenhancer.client.domain.mdbspringboot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class CloudletInfo {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalIPAddress() {
        return localIPAddress;
    }

    public void setLocalIPAddress(String localIPAddress) {
        this.localIPAddress = localIPAddress;
    }

    public String getGlobalIPAddress() {
        return globalIPAddress;
    }

    public void setGlobalIPAddress(String globalIPAddress) {
        this.globalIPAddress = globalIPAddress;
    }

    public String getGPSLocation() {
        return GPSLocation;
    }

    public void setGPSLocation(String GPSLocation) {
        this.GPSLocation = GPSLocation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String name;

    private String localIPAddress;

    private String globalIPAddress;

    private String GPSLocation;
    private String category;
    public CloudletInfo(int id, String name, String localIPAddress, String globalIPAddress, String category)
    {
        super();
        this.id = id;
        this.name = name;
        this.localIPAddress = localIPAddress;
        this.globalIPAddress = globalIPAddress;
        this.category = category;
    }

}
