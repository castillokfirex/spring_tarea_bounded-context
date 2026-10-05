package com.example.tarea.domain.medicationroute.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.medicationroute.event.MedicationRouteDeletedEvent;
import com.example.tarea.domain.medicationroute.event.MedicationRouteRegisteredEvent;
import com.example.tarea.domain.medicationroute.event.MedicationRouteUpdatedEvent;
import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;

/**
 * Aggregate root del bounded context <b>medicationroute</b> (tabla <code>medication_routes</code>).
 */
public class MedicationRoute extends AggregateRoot {

    private final MedicationRouteId id;
    private String code;
    private String name;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private MedicationRoute(
            MedicationRouteId id,
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
    public static MedicationRoute register(
            String code,
            String name,
            Boolean active) {

        LocalDateTime now = LocalDateTime.now();
        MedicationRoute aggregate = new MedicationRoute(
                MedicationRouteId.generate(),
                code,
                name,
                active,
                now,
                now);

        aggregate.recordEvent(new MedicationRouteRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static MedicationRoute restore(
            MedicationRouteId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new MedicationRoute(
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

        recordEvent(new MedicationRouteUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new MedicationRouteDeletedEvent(this.id, LocalDateTime.now()));
    }

    public MedicationRouteId id() {
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
