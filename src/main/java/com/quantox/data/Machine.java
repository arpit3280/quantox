package com.quantox.data;

import com.quantox.constant.MachineStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "machine")
public class Machine {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String uid;
    @Enumerated(EnumType.STRING)
    @NotNull
    private MachineStatus status;
    @NotNull
    private long createdBy;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private boolean active;

    public Machine(long id, String uid, MachineStatus status, long createdBy, LocalDateTime createdAt, boolean active) {
        this.id = id;
        this.uid = uid;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.active = active;
    }

    public Machine() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
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
