package com.janitha.videoenhancer.client.external.models;

import com.janitha.videoenhancer.client.domain.mdbspringboot.model.PyProcess;

import java.util.Map;

public class PyProcessDetails {
    public PyProcessDetails(Map<Integer, PyProcess> mapOfProcess) {
        this.mapOfProcess = mapOfProcess;
    }

    public Map<Integer, PyProcess> getMapOfProcess() {
        return mapOfProcess;
    }

    public void setMapOfProcess(Map<Integer, PyProcess> mapOfProcess) {
        this.mapOfProcess = mapOfProcess;
    }

    Map<Integer, PyProcess> mapOfProcess;



}
