package com.janitha.videoenhancer.client.external.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janitha.videoenhancer.client.external.models.plugIn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class plugInExternalManager {
    Map<String, plugIn> plugInList = new HashMap<>();

    public Map<String, plugIn> getAvailablePlugIns(){
        return plugInList;
    }

    @Value("${python.pluginFilePath}")
    private String pluginFilePath;

    @Value("${python.pythonPath}")
    private String pythonPath;
    public void populatePluIngs() throws IOException, InterruptedException {
        plugInList.clear();
        List<File> infoFileList = readInfoFiles();
        for (File file : infoFileList) {
            ObjectMapper mapper = new ObjectMapper();
            plugIn tmp = mapper.readValue(new File(file.getPath()), plugIn.class);
            plugInList.put(tmp.getName(),tmp);
        }
    }

    private  List<File>  readInfoFiles() throws IOException {

        List<File> infoFileList = new ArrayList<>();
        File currentDirFile = new File(pluginFilePath);
        if (currentDirFile.isDirectory()) {
            File[] listOfFiles = currentDirFile.listFiles();
            if (listOfFiles.length < 1)System.out.println(
                    "There is no File inside Folder");
            else System.out.println("List of Files & Folder");
            for (File file : listOfFiles) {
                if(!file.isDirectory()){
                    if(file.getName().toLowerCase().endsWith(".info"))
                    {
                        infoFileList.add(file);
                    }
                }
            }
        }
        else System.out .println("There is no Folder @ given path :" + currentDirFile);

        return infoFileList;
    }
}
