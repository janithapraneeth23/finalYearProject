package com.janitha.videoenhancer.client.app.controller;

import com.janitha.videoenhancer.client.domain.mdbspringboot.model.PyProcess;
import com.janitha.videoenhancer.client.domain.services.PythonProcessManager;
import com.janitha.videoenhancer.client.external.models.PyProcessDetails;
import com.janitha.videoenhancer.client.external.models.plugIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;


@RestController
public class AdminPage extends BaseController{

    @Autowired
    PythonProcessManager pythonProcessManagerOBJ;


    @GetMapping("adminPage/getRunningProcesses")
    public String getRunningProcesses() throws IOException, InterruptedException
    {
        Map<Integer, PyProcess> mapOfProcess = pythonProcessManagerOBJ.getMapOfProcess();
        PyProcessDetails result = new PyProcessDetails(mapOfProcess);
        return "NONE";
    }

    @GetMapping("adminPage/closeRunnigProcess")
    public String closeRunnigProcess() throws IOException, InterruptedException
    {
        pythonProcessManagerOBJ.killAllProcess();
        return "Done";
    }
}
