package com.assignment.server.models;

public class UpdateServer {

    private String hostname;
    private String serialNumber;
    private String operatingSystem;
    private boolean active;

    public UpdateServer(String hostname, String serialNumber, String operatingSystem, boolean active) {
        this.hostname = hostname;
        this.serialNumber = serialNumber;
        this.operatingSystem = operatingSystem;
        this.active = active;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active){
        this.active = active;
    }




    public void setActive(boolean active) {
        this.active = active;
    }
}
