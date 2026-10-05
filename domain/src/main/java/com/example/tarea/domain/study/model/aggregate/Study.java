package com.example.tarea.domain.study.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.study.event.StudyDeletedEvent;
import com.example.tarea.domain.study.event.StudyRegisteredEvent;
import com.example.tarea.domain.study.event.StudyUpdatedEvent;
import com.example.tarea.domain.study.model.valueobject.StudyId;

/**
 * Aggregate root del bounded context <b>study</b> (tabla <code>studies</code>).
 */
public class Study extends AggregateRoot {

    private final StudyId id;
    private String name;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Study(
            StudyId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 40, "name");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static Study register(
            String name) {

        LocalDateTime now = LocalDateTime.now();
        Study aggregate = new Study(
                StudyId.generate(),
                name,
                now,
                now);

        aggregate.recordEvent(new StudyRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static Study restore(
            StudyId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Study(
                id,
                name,
                createdAt,
                updatedAt);
    }

    public void update(
            String name) {

        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 40, "name");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new StudyUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new StudyDeletedEvent(this.id, LocalDateTime.now()));
    }

    public StudyId id() {
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
