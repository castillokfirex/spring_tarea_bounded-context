package com.example.tarea.domain.gender.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.gender.event.GenderDeletedEvent;
import com.example.tarea.domain.gender.event.GenderRegisteredEvent;
import com.example.tarea.domain.gender.event.GenderUpdatedEvent;
import com.example.tarea.domain.gender.model.valueobject.GenderId;

/**
 * Aggregate root del bounded context <b>gender</b> (tabla <code>genders</code>).
 */
public class Gender extends AggregateRoot {

    private final GenderId id;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Gender(
            GenderId id,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.description = Guard.maxLength(Guard.notBlank(description, "description"), 50, "description");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static Gender register(
            String description) {

        LocalDateTime now = LocalDateTime.now();
        Gender aggregate = new Gender(
                GenderId.generate(),
                description,
                now,
                now);

        aggregate.recordEvent(new GenderRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static Gender restore(
            GenderId id,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Gender(
                id,
                description,
                createdAt,
                updatedAt);
    }

    public void update(
            String description) {

        this.description = Guard.maxLength(Guard.notBlank(description, "description"), 50, "description");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new GenderUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new GenderDeletedEvent(this.id, LocalDateTime.now()));
    }

    public GenderId id() {
        return id;
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
