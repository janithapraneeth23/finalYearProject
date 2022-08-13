package com.janitha.videoenhancer.client.domain.mdbspringboot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("clouldletitems")
public class CloudletInfo {
    private String name;
    private String ipAddress;

    private String GPSLocation;
    private String category;

    public String getItemDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(this);
        return json;
    }
    public CloudletInfo(String id, String name, String ipAddress, String category)
    {
        super();
        this.name = name;
        this.ipAddress = ipAddress;
        this.category = category;
    }

}
