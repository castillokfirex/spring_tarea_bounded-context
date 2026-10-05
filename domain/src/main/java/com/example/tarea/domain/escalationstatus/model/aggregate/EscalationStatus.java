package com.example.tarea.domain.escalationstatus.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.escalationstatus.event.EscalationStatusDeletedEvent;
import com.example.tarea.domain.escalationstatus.event.EscalationStatusRegisteredEvent;
import com.example.tarea.domain.escalationstatus.event.EscalationStatusUpdatedEvent;
import com.example.tarea.domain.escalationstatus.model.valueobject.EscalationStatusId;

/**
 * Aggregate root del bounded context <b>escalationstatus</b> (tabla <code>escalations_statuses</code>).
 */
public class EscalationStatus extends AggregateRoot {

    private final EscalationStatusId id;
    private String nameStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EscalationStatus(
            EscalationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.nameStatus = Guard.maxLength(Guard.notBlank(nameStatus, "nameStatus"), 50, "nameStatus");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static EscalationStatus register(
            String nameStatus) {

        LocalDateTime now = LocalDateTime.now();
        EscalationStatus aggregate = new EscalationStatus(
                EscalationStatusId.generate(),
                nameStatus,
                now,
                now);

        aggregate.recordEvent(new EscalationStatusRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static EscalationStatus restore(
            EscalationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EscalationStatus(
                id,
                nameStatus,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameStatus) {

        this.nameStatus = Guard.maxLength(Guard.notBlank(nameStatus, "nameStatus"), 50, "nameStatus");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new EscalationStatusUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new EscalationStatusDeletedEvent(this.id, LocalDateTime.now()));
    }

    public EscalationStatusId id() {
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
