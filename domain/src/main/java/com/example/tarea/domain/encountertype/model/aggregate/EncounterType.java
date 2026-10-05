package com.example.tarea.domain.encountertype.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.encountertype.event.EncounterTypeDeletedEvent;
import com.example.tarea.domain.encountertype.event.EncounterTypeRegisteredEvent;
import com.example.tarea.domain.encountertype.event.EncounterTypeUpdatedEvent;
import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;

/**
 * Aggregate root del bounded context <b>encountertype</b> (tabla <code>encounter_types</code>).
 */
public class EncounterType extends AggregateRoot {

    private final EncounterTypeId id;
    private String code;
    private String name;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EncounterType(
            EncounterTypeId id,
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
    public static EncounterType register(
            String code,
            String name,
            Boolean active) {

        LocalDateTime now = LocalDateTime.now();
        EncounterType aggregate = new EncounterType(
                EncounterTypeId.generate(),
                code,
                name,
                active,
                now,
                now);

        aggregate.recordEvent(new EncounterTypeRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static EncounterType restore(
            EncounterTypeId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EncounterType(
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

        recordEvent(new EncounterTypeUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new EncounterTypeDeletedEvent(this.id, LocalDateTime.now()));
    }

    public EncounterTypeId id() {
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
