package com.example.tarea.domain.consenttype.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.consenttype.event.ConsentTypeDeletedEvent;
import com.example.tarea.domain.consenttype.event.ConsentTypeRegisteredEvent;
import com.example.tarea.domain.consenttype.event.ConsentTypeUpdatedEvent;
import com.example.tarea.domain.consenttype.model.valueobject.ConsentTypeId;

/**
 * Aggregate root del bounded context <b>consenttype</b> (tabla <code>consent_types</code>).
 */
public class ConsentType extends AggregateRoot {

    private final ConsentTypeId id;
    private String code;
    private String name;
    private Boolean active;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ConsentType(
            ConsentTypeId id,
            String code,
            String name,
            Boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.description = description;
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ConsentType register(
            String code,
            String name,
            Boolean active,
            String description) {

        LocalDateTime now = LocalDateTime.now();
        ConsentType aggregate = new ConsentType(
                ConsentTypeId.generate(),
                code,
                name,
                active,
                description,
                now,
                now);

        aggregate.recordEvent(new ConsentTypeRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ConsentType restore(
            ConsentTypeId id,
            String code,
            String name,
            Boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ConsentType(
                id,
                code,
                name,
                active,
                description,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            Boolean active,
            String description) {

        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.description = description;
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ConsentTypeUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ConsentTypeDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ConsentTypeId id() {
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

    public String description() {
        return description;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
