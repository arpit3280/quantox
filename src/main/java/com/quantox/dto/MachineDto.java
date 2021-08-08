package com.quantox.dto;

import com.quantox.constant.MachineStatus;

import java.time.LocalDateTime;

public class MachineDto {
    private String uid;
    private MachineStatus status;
    private String createdBy;
    private LocalDateTime createdAt;
    private boolean active;

    public MachineDto() {
    }

    public MachineDto(String uid, MachineStatus status, String createdBy, LocalDateTime createdAt, boolean active) {
        this.uid = uid;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.active = active;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public MachineStatus getStatus() {
        return status;
    }

    public void setStatus(MachineStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
