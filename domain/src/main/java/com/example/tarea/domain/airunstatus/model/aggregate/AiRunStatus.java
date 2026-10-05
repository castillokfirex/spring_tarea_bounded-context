package com.example.tarea.domain.airunstatus.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.airunstatus.event.AiRunStatusDeletedEvent;
import com.example.tarea.domain.airunstatus.event.AiRunStatusRegisteredEvent;
import com.example.tarea.domain.airunstatus.event.AiRunStatusUpdatedEvent;
import com.example.tarea.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>airunstatus</b> (tabla <code>ai_runs_statuses</code>).
 */
public class AiRunStatus extends AggregateRoot {

    private final AiRunStatusId id;
    private String nameStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AiRunStatus(
            AiRunStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.nameStatus = Guard.maxLength(Guard.notBlank(nameStatus, "nameStatus"), 50, "nameStatus");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static AiRunStatus register(
            String nameStatus) {

        LocalDateTime now = LocalDateTime.now();
        AiRunStatus aggregate = new AiRunStatus(
                AiRunStatusId.generate(),
                nameStatus,
                now,
                now);

        aggregate.recordEvent(new AiRunStatusRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static AiRunStatus restore(
            AiRunStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AiRunStatus(
                id,
                nameStatus,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameStatus) {

        this.nameStatus = Guard.maxLength(Guard.notBlank(nameStatus, "nameStatus"), 50, "nameStatus");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new AiRunStatusUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new AiRunStatusDeletedEvent(this.id, LocalDateTime.now()));
    }

    public AiRunStatusId id() {
        return id;
    }

    public String nameStatus() {
        return nameStatus;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
