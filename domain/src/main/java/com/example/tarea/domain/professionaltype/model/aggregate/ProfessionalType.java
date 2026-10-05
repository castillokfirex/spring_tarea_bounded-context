package com.example.tarea.domain.professionaltype.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.professionaltype.event.ProfessionalTypeDeletedEvent;
import com.example.tarea.domain.professionaltype.event.ProfessionalTypeRegisteredEvent;
import com.example.tarea.domain.professionaltype.event.ProfessionalTypeUpdatedEvent;
import com.example.tarea.domain.professionaltype.model.valueobject.ProfessionalTypeId;

/**
 * Aggregate root del bounded context <b>professionaltype</b> (tabla <code>professional_types</code>).
 */
public class ProfessionalType extends AggregateRoot {

    private final ProfessionalTypeId id;
    private String name;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProfessionalType(
            ProfessionalTypeId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 40, "name");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ProfessionalType register(
            String name) {

        LocalDateTime now = LocalDateTime.now();
        ProfessionalType aggregate = new ProfessionalType(
                ProfessionalTypeId.generate(),
                name,
                now,
                now);

        aggregate.recordEvent(new ProfessionalTypeRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ProfessionalType restore(
            ProfessionalTypeId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ProfessionalType(
                id,
                name,
                createdAt,
                updatedAt);
    }

    public void update(
            String name) {

        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 40, "name");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ProfessionalTypeUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ProfessionalTypeDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ProfessionalTypeId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
