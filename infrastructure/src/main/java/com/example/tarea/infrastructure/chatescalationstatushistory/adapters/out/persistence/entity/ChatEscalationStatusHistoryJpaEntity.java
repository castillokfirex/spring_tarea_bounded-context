package com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_escalation_status_history")
public class ChatEscalationStatusHistoryJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "escalation_id", nullable = false)
    private UUID escalationId;

    @Column(name = "escalation_status_id", nullable = false)
    private UUID escalationStatusId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;

    public ChatEscalationStatusHistoryJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEscalationId() {
        return escalationId;
    }

    public void setEscalationId(UUID escalationId) {
        this.escalationId = escalationId;
    }

    public UUID getEscalationStatusId() {
        return escalationStatusId;
    }

    public void setEscalationStatusId(UUID escalationStatusId) {
        this.escalationStatusId = escalationStatusId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}
