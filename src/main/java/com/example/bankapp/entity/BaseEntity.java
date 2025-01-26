package com.example.bankapp.entity;
import jakarta.persistence.MappedSuperclass;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    @Column(updatable=false,name = "created_at",nullable=false)
    private LocalDateTime createdAt;

    @Column(updatable=false,name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by",nullable=false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        System.out.println("createdBy: " + createdBy);
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
