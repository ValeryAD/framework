package com.github.valeryad.model;

import java.util.Objects;

public class Machine {

    private int numberInstances;
    private String osAndSoftware;
    private String vmClass;
    private String series;
    private String type;
    private int numberGPUs;
    private String gpuType;
    private String localSSD;
    private String region;
    private String committedUsage;


    public int getNumberInstances() {
        return numberInstances;
    }

    public void setNumberInstances(int numberInstances) {
        this.numberInstances = numberInstances;
    }

    public String getOsAndSoftware() {
        return osAndSoftware;
    }

    public void setOsAndSoftware(String osAndSoftware) {
        this.osAndSoftware = osAndSoftware;
    }

    public String getVmClass() {
        return vmClass;
    }

    public void setVmClass(String vmClass) {
        this.vmClass = vmClass;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberGPUs() {
        return numberGPUs;
    }

    public void setNumberGPUs(int numberGPUs) {
        this.numberGPUs = numberGPUs;
    }

    public String getGpuType() {
        return gpuType;
    }

    public void setGPUType(String gpuType) {
        this.gpuType = gpuType;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    public void setCommittedUsage(String committedUsage) {
        this.committedUsage = committedUsage;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + numberInstances;
        result = prime * result + numberGPUs;
        return prime * result + Objects.hash(osAndSoftware, vmClass, series, type, gpuType, localSSD, region, committedUsage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Machine machine = (Machine) obj;

        if (numberInstances != machine.numberInstances || numberGPUs != machine.numberGPUs) return false;

        return Objects.equals(osAndSoftware, machine.osAndSoftware) &&
                Objects.equals(vmClass, machine.vmClass) &&
                Objects.equals(series, machine.series) &&
                Objects.equals(type, machine.type) &&
                Objects.equals(gpuType, machine.gpuType) &&
                Objects.equals(localSSD, machine.localSSD) &&
                Objects.equals(region, machine.region) &&
                Objects.equals(committedUsage, machine.committedUsage);
    }

    @Override
    public String toString() {
        return "Machine{" +
                "number of instances='" + numberInstances + '\'' +
                ", osAndSoftware='" + osAndSoftware + '\'' +
                ", vmClass='" + vmClass + '\'' +
                ", series='" + series + '\'' +
                ", type='" + type + '\'' +
                ", gpusNumber='" + numberGPUs + '\'' +
                ", gpuType='" + gpuType + '\'' +
                ", localSSD='" + localSSD + '\'' +
                ", region='" + region + '\'' +
                ", committedUsage='" + committedUsage + '\'' +
                '}';
    }
}
