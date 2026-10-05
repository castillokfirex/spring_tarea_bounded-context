package com.example.tarea.domain.sendertype.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.sendertype.event.SenderTypeDeletedEvent;
import com.example.tarea.domain.sendertype.event.SenderTypeRegisteredEvent;
import com.example.tarea.domain.sendertype.event.SenderTypeUpdatedEvent;
import com.example.tarea.domain.sendertype.model.valueobject.SenderTypeId;

/**
 * Aggregate root del bounded context <b>sendertype</b> (tabla <code>sender_types</code>).
 */
public class SenderType extends AggregateRoot {

    private final SenderTypeId id;
    private String nameType;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SenderType(
            SenderTypeId id,
            String nameType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.nameType = Guard.maxLength(Guard.notBlank(nameType, "nameType"), 50, "nameType");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static SenderType register(
            String nameType) {

        LocalDateTime now = LocalDateTime.now();
        SenderType aggregate = new SenderType(
                SenderTypeId.generate(),
                nameType,
                now,
                now);

        aggregate.recordEvent(new SenderTypeRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static SenderType restore(
            SenderTypeId id,
            String nameType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new SenderType(
                id,
                nameType,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameType) {

        this.nameType = Guard.maxLength(Guard.notBlank(nameType, "nameType"), 50, "nameType");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new SenderTypeUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new SenderTypeDeletedEvent(this.id, LocalDateTime.now()));
    }

    public SenderTypeId id() {
        return id;
    }

    public String nameType() {
        return nameType;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
