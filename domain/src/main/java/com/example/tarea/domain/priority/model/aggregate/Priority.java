package com.example.tarea.domain.priority.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.priority.event.PriorityDeletedEvent;
import com.example.tarea.domain.priority.event.PriorityRegisteredEvent;
import com.example.tarea.domain.priority.event.PriorityUpdatedEvent;
import com.example.tarea.domain.priority.model.valueobject.PriorityId;

/**
 * Aggregate root del bounded context <b>priority</b> (tabla <code>priorities</code>).
 */
public class Priority extends AggregateRoot {

    private final PriorityId id;
    private String namePriority;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Priority(
            PriorityId id,
            String namePriority,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.namePriority = Guard.maxLength(Guard.notBlank(namePriority, "namePriority"), 50, "namePriority");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static Priority register(
            String namePriority) {

        LocalDateTime now = LocalDateTime.now();
        Priority aggregate = new Priority(
                PriorityId.generate(),
                namePriority,
                now,
                now);

        aggregate.recordEvent(new PriorityRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static Priority restore(
            PriorityId id,
            String namePriority,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Priority(
                id,
                namePriority,
                createdAt,
                updatedAt);
    }

    public void update(
            String namePriority) {

        this.namePriority = Guard.maxLength(Guard.notBlank(namePriority, "namePriority"), 50, "namePriority");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new PriorityUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new PriorityDeletedEvent(this.id, LocalDateTime.now()));
    }

    public PriorityId id() {
        return id;
    }

    public String namePriority() {
        return namePriority;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
