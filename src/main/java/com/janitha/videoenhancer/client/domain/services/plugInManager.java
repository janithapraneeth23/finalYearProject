package com.janitha.videoenhancer.client.domain.services;

import com.janitha.videoenhancer.client.domain.mdbspringboot.model.cloudletPluginArguments;
import com.janitha.videoenhancer.client.external.models.plugIn;
import com.janitha.videoenhancer.client.external.models.responseReqPlugin;
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

    @Value("${server.address}")
    private String ipAddress;
    public responseReqPlugin involveAPlugin(String plugInName, String URL) throws IOException, InterruptedException {

        Map<String, plugIn> plugInList = getAvailablePlugIns();
        String xmlOutput = "Completerd";
        String host = ipAddress;
        URL = "https://www.youtube.com/watch?v=2fmkmp-_KH0";
        if(plugInList.containsKey(plugInName)){
            plugIn tmp = plugInList.get(plugInName);
            cloudletPluginArguments pluginArguments = new cloudletPluginArguments("0", URL);
            ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, pluginFilePath + tmp.getFileName(), host, pluginArguments.getPort(), pluginArguments.getVideoURL());
            processBuilder.redirectErrorStream(true);
            System.out.println(plugInName + "  filePath " + tmp.getFileName() +  " " + URL + " " + pluginArguments.getPort() + "\n");
            Process process = processBuilder.start();
            System.out.println(host + " " + plugInName + "  filePath2 " + tmp.getFileName() +  " " + URL + " " + pluginArguments.getPort() + "\n");

            InputStream stream = (process.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            long end = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(5);

            //while (System.currentTimeMillis() < end && (line = reader.readLine()) != null) {
             //   stringBuilder.append(line);
            //}
            stream.close();
            xmlOutput = stringBuilder.toString();
            System.out.println(xmlOutput);

            return new responseReqPlugin(true, host, pluginArguments.getPort());
            //int exitCode = process.waitFor();

        }
        return new responseReqPlugin(false, "", "");
    }


}
