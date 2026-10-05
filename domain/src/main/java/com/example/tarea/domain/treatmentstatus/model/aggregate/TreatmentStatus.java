package com.example.tarea.domain.treatmentstatus.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.treatmentstatus.event.TreatmentStatusDeletedEvent;
import com.example.tarea.domain.treatmentstatus.event.TreatmentStatusRegisteredEvent;
import com.example.tarea.domain.treatmentstatus.event.TreatmentStatusUpdatedEvent;
import com.example.tarea.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

/**
 * Aggregate root del bounded context <b>treatmentstatus</b> (tabla <code>treatment_statusses</code>).
 */
public class TreatmentStatus extends AggregateRoot {

    private final TreatmentStatusId id;
    private String code;
    private String name;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentStatus(
            TreatmentStatusId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static TreatmentStatus register(
            String code,
            String name,
            Boolean active) {

        LocalDateTime now = LocalDateTime.now();
        TreatmentStatus aggregate = new TreatmentStatus(
                TreatmentStatusId.generate(),
                code,
                name,
                active,
                now,
                now);

        aggregate.recordEvent(new TreatmentStatusRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static TreatmentStatus restore(
            TreatmentStatusId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentStatus(
                id,
                code,
                name,
                active,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            Boolean active) {

        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new TreatmentStatusUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new TreatmentStatusDeletedEvent(this.id, LocalDateTime.now()));
    }

    public TreatmentStatusId id() {
        return id;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }

    public Boolean active() {
        return active;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
