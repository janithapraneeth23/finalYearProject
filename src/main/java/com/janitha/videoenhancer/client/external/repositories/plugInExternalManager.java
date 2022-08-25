package com.janitha.videoenhancer.client.external.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janitha.videoenhancer.client.external.models.plugIn;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class plugInExternalManager {
    List<plugIn> plugInList = new ArrayList<>();

    public List<plugIn> getAvailablePlugIns(){
        return plugInList;
    }

    public void populatePluIngs() throws IOException{
        List<File> infoFileList = readInfoFiles();
        for (File file : infoFileList) {
            ObjectMapper mapper = new ObjectMapper();
            plugIn tmp = mapper.readValue(new File(file.getPath()), plugIn.class);
            tmp.setFileName(file.getParent() +"\\" + tmp.getFileName());
            plugInList.add(tmp);
        }

        for(plugIn tmp : plugInList){
            System.out.println(tmp.getFileName());
            ProcessBuilder processBuilder = new ProcessBuilder("python", resolvePythonScriptPath("hello.py"));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            List<String> results = readProcessOutput(process.getInputStream());

            assertThat("Results should not be empty", results, is(not(empty())));
            assertThat("Results should contain output of script: ", results, hasItem(
                    containsString("Hello Baeldung Readers!!")));

            int exitCode = process.waitFor();
            assertEquals("No errors should be detected", 0, exitCode);
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
