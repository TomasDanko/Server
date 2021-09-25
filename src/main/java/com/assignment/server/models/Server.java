package com.assignment.server.models;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@DynamicInsert
@DynamicUpdate
public class Server {

    @Id
    @GeneratedValue
    private UUID id;
    @NonNull
    @NotNull(message = "Hostname is required")
    private String hostname;
    @NonNull
    @NotNull(message = "Serial number is required")
    private String serialNumber;
    @NonNull
    @NotNull(message = "Serial number is required")
    private String operatingSystem;
    private boolean active;
    private Date createdAt;

    public Server() {}


    public Server(UUID id, @NonNull String hostname, @NonNull String serialNumber, @NonNull String operatingSystem, boolean active, Date createdAt) {
        this.id = id;
        this.hostname = hostname;
        this.serialNumber = serialNumber;
        this.operatingSystem = operatingSystem;
        this.active = active;
        this.createdAt = createdAt;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @NonNull
    public String getHostname() {
        return hostname;
    }

    public void setHostname(@NonNull String hostname) {
        this.hostname = hostname;
    }

    @NonNull
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(@NonNull String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @NonNull
    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(@NonNull String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
