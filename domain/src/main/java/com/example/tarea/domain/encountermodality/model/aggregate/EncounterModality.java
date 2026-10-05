package com.example.tarea.domain.encountermodality.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.encountermodality.event.EncounterModalityDeletedEvent;
import com.example.tarea.domain.encountermodality.event.EncounterModalityRegisteredEvent;
import com.example.tarea.domain.encountermodality.event.EncounterModalityUpdatedEvent;
import com.example.tarea.domain.encountermodality.model.valueobject.EncounterModalityId;

/**
 * Aggregate root del bounded context <b>encountermodality</b> (tabla <code>encounter_modalities</code>).
 */
public class EncounterModality extends AggregateRoot {

    private final EncounterModalityId id;
    private String code;
    private String name;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EncounterModality(
            EncounterModalityId id,
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
    public static EncounterModality register(
            String code,
            String name,
            Boolean active) {

        LocalDateTime now = LocalDateTime.now();
        EncounterModality aggregate = new EncounterModality(
                EncounterModalityId.generate(),
                code,
                name,
                active,
                now,
                now);

        aggregate.recordEvent(new EncounterModalityRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static EncounterModality restore(
            EncounterModalityId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EncounterModality(
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

        recordEvent(new EncounterModalityUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new EncounterModalityDeletedEvent(this.id, LocalDateTime.now()));
    }

    public EncounterModalityId id() {
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
