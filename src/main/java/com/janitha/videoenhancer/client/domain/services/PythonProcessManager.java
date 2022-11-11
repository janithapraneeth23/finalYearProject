package com.janitha.videoenhancer.client.domain.services;

import com.janitha.videoenhancer.client.domain.mdbspringboot.model.PyProcess;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Component
public class PythonProcessManager {
    Map<Integer, PyProcess> mapOfProcess;

    static int id = 0;

    public void killAllProcess()
    {
        for (Map.Entry<Integer, PyProcess> mapElement : mapOfProcess.entrySet()) {
            Integer key = mapElement.getKey();

            // Adding some bonus marks to all the students
            PyProcess value = mapElement.getValue();
            value.getProcess().destroy();
        }

    }

    @PreDestroy
    public void preDestroy()
    {
        killAllProcess();
    }
    public Map<Integer, PyProcess> getMapOfProcess() {
        return mapOfProcess;
    }

    public void setMapOfProcess(Map<Integer, PyProcess> mapOfProcess) {
        this.mapOfProcess = mapOfProcess;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        PythonProcessManager.id = id;
    }

    public void addAProcess(PyProcess newPyProcess)
    {
        mapOfProcess.put(++id, newPyProcess);
    }

    public Map<Integer, PyProcess> getProcessInformation()
    {
        return mapOfProcess;
    }

    public PythonProcessManager() {
        this.mapOfProcess = new HashMap<>();

    }
}
