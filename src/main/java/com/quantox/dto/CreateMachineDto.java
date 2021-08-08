package com.quantox.dto;

import com.quantox.constant.MachineStatus;

import java.time.LocalDateTime;

public class CreateMachineDto {
    private long id;
    private String uid;
    private MachineStatus status;
    private String createdBy;
    private LocalDateTime createdAt;
    private boolean active;

    public CreateMachineDto() {
    }

    public CreateMachineDto(long id, String uid, MachineStatus status, String createdBy, LocalDateTime createdAt, boolean active) {
        this.id = id;
        this.uid = uid;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.active = active;
    }
}
