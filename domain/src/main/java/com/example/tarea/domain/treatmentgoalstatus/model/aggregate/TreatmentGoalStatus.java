package com.example.tarea.domain.treatmentgoalstatus.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.treatmentgoalstatus.event.TreatmentGoalStatusDeletedEvent;
import com.example.tarea.domain.treatmentgoalstatus.event.TreatmentGoalStatusRegisteredEvent;
import com.example.tarea.domain.treatmentgoalstatus.event.TreatmentGoalStatusUpdatedEvent;
import com.example.tarea.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

/**
 * Aggregate root del bounded context <b>treatmentgoalstatus</b> (tabla <code>treatment_goal_statusses</code>).
 */
public class TreatmentGoalStatus extends AggregateRoot {

    private final TreatmentGoalStatusId id;
    private String code;
    private String name;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentGoalStatus(
            TreatmentGoalStatusId id,
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
    public static TreatmentGoalStatus register(
            String code,
            String name,
            Boolean active) {

        LocalDateTime now = LocalDateTime.now();
        TreatmentGoalStatus aggregate = new TreatmentGoalStatus(
                TreatmentGoalStatusId.generate(),
                code,
                name,
                active,
                now,
                now);

        aggregate.recordEvent(new TreatmentGoalStatusRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static TreatmentGoalStatus restore(
            TreatmentGoalStatusId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentGoalStatus(
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

        recordEvent(new TreatmentGoalStatusUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new TreatmentGoalStatusDeletedEvent(this.id, LocalDateTime.now()));
    }

    public TreatmentGoalStatusId id() {
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
