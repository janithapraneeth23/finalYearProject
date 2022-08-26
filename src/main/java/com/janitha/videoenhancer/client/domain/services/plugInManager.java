package com.janitha.videoenhancer.client.domain.services;

import com.janitha.videoenhancer.client.external.models.plugIn;
import com.janitha.videoenhancer.client.external.repositories.plugInExternalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class plugInManager {

    @Autowired
    plugInExternalManager plugInExternalManagerOBJ;


    public Map<String, plugIn> getAvailablePlugIns(){
        return plugInExternalManagerOBJ.getAvailablePlugIns();
    }

    public void populatePluIngs() throws IOException, InterruptedException {
        plugInExternalManagerOBJ.populatePluIngs();
    }

    @Value("${python.pluginFilePath}")
    private String pluginFilePath;

    @Value("${python.pythonPath}")
    private String pythonPath;
    public String involveAPlugin(String plugInName) throws IOException, InterruptedException {
        Map<String, plugIn> plugInList = getAvailablePlugIns();
        String xmlOutput = "";
        if(plugInList.containsKey(plugInName)){
            plugIn tmp = plugInList.get(plugInName);
            ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, pluginFilePath + tmp.getFileName());
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
            xmlOutput = stringBuilder.toString();
            System.out.println(xmlOutput);

            int exitCode = process.waitFor();

        }
        return xmlOutput;
    }


}
