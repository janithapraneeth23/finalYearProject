package com.janitha.videoenhancer.client.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.janitha.videoenhancer.client.domain.services.plugInManager;
import com.janitha.videoenhancer.client.external.models.plugIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class cloudletDashboard extends BaseController{

    /*
    @Autowired
    cloudletManager cloudletManagerObj;

    @GetMapping("/getAvailableCloudlets")
    public String getAvailableCloudlets() throws JsonProcessingException {
        String strCloudletes = "";
        List<CloudletInfo> cloudletInfoList = cloudletManagerObj.getAllCloudlets();
        for (CloudletInfo temp : cloudletInfoList) {
            strCloudletes = strCloudletes.concat(temp.getItemDetails());
        }
        return strCloudletes;
    }

    @Autowired
    cloudletRepository cloudletRepo;
    @GetMapping("/create")
    public void index2() {
        System.out.println("Data creation started...");
        cloudletRepo.save(new CloudletInfo("1", "janitha", "192.323", ""));
        cloudletRepo.save(new CloudletInfo("2", "janitha", "192.323", "jani"));
        System.out.println("Data creation complete...");

    }

    @Autowired gcpPubSubManager gcpPubSubManagerObj;

    @Autowired
    private ServerApplication.PubsubOutboundGateway messagingGateway;
    // end::autowireGateway[]


    @GetMapping("/publishMessage/{message}")
    public RedirectView publishMessage(@PathVariable("message") String message) {
        messagingGateway.sendToPubsub(message);
        return new RedirectView("/");
    }
*/
    @Autowired
    plugInManager plugInManagerObj;


    @GetMapping("/updateAvailablePlugins")
    public void updateAvailablePlugins() throws IOException, InterruptedException {
        plugInManagerObj.populatePluIngs();
    }
    @GetMapping("/getAvailablePlugins")
    public String getAvailableCloudlets() throws IOException, InterruptedException {
        String strCloudletes = "";
        Map<String, plugIn> plugInMap =  plugInManagerObj.getAvailablePlugIns();
        for (Map.Entry<String, plugIn> entry : plugInMap.entrySet()) {
            strCloudletes += "<option value=" +  entry.getKey() + ">" + entry.getKey() + "</option>";

        }
        //strCloudletes += ("</select>");
        return strCloudletes;
    }

    @GetMapping("/involkePlugIn/{pluinName}/{videoURL}")
    public String involkePlugIn(@PathVariable("pluinName") String pluinName, @PathVariable("videoURL") String videoURL) throws IOException, InterruptedException {
        String New_URL = plugInManagerObj.involveAPlugin(pluinName, videoURL);
        String NewText = "<a href=\"/" + New_URL +"\">click Here</a>";
        return NewText;
    }

}
