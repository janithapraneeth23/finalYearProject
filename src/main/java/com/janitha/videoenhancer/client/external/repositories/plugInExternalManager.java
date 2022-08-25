package com.janitha.videoenhancer.client.external.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janitha.videoenhancer.client.external.models.plugIn;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class plugInExternalManager {
    List<plugIn> plugInList = new ArrayList<>();

    public List<plugIn> getAvailablePlugIns(){
        return plugInList;
    }

    public void populatePluIngs() throws IOException, InterruptedException {
        List<File> infoFileList = readInfoFiles();
        for (File file : infoFileList) {
            ObjectMapper mapper = new ObjectMapper();
            plugIn tmp = mapper.readValue(new File(file.getPath()), plugIn.class);
            tmp.setFileName(file.getParent() +"\\" + tmp.getFileName());
            plugInList.add(tmp);
        }

        for(plugIn tmp : plugInList){
            System.out.println(tmp.getFileName());
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\janitha\\AppData\\Local\\Programs\\Python\\Python310\\python", "C:\\Personal\\Msc\\Project\\server\\server\\src\\main\\java\\com\\janitha\\videoenhancer\\client\\external\\cloudletPlugins\\BlackAndWhitePlugin.py");
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            InputStream stream = (process.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            long end = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(5);

            while (System.currentTimeMillis() < end && (line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            stream.close();
            String xmlOutput = stringBuilder.toString();
            System.out.println(xmlOutput);

            int exitCode = process.waitFor();

        }
    }

    private  List<File>  readInfoFiles() throws IOException {

        List<File> infoFileList = new ArrayList<>();
        File currentDirFile = new File("..\\server\\src\\main\\java\\com\\janitha\\videoenhancer\\client\\external\\cloudletPlugins");
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
