package com.janitha.videoenhancer.client.domain.services;

import com.janitha.videoenhancer.client.external.models.plugIn;
import com.janitha.videoenhancer.client.external.repositories.plugInExternalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class plugInManager {

    @Autowired
    plugInExternalManager plugInExternalManagerOBJ;


    public List<plugIn> getAvailablePlugIns(){
        return plugInExternalManagerOBJ.getAvailablePlugIns();
    }

    public void populatePluIngs() throws IOException, InterruptedException {
        plugInExternalManagerOBJ.populatePluIngs();
    }



}
