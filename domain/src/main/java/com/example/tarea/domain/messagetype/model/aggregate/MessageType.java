package com.example.tarea.domain.messagetype.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.messagetype.event.MessageTypeDeletedEvent;
import com.example.tarea.domain.messagetype.event.MessageTypeRegisteredEvent;
import com.example.tarea.domain.messagetype.event.MessageTypeUpdatedEvent;
import com.example.tarea.domain.messagetype.model.valueobject.MessageTypeId;

/**
 * Aggregate root del bounded context <b>messagetype</b> (tabla <code>message_types</code>).
 */
public class MessageType extends AggregateRoot {

    private final MessageTypeId id;
    private String nameType;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private MessageType(
            MessageTypeId id,
            String nameType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.nameType = Guard.maxLength(Guard.notBlank(nameType, "nameType"), 50, "nameType");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static MessageType register(
            String nameType) {

        LocalDateTime now = LocalDateTime.now();
        MessageType aggregate = new MessageType(
                MessageTypeId.generate(),
                nameType,
                now,
                now);

        aggregate.recordEvent(new MessageTypeRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static MessageType restore(
            MessageTypeId id,
            String nameType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new MessageType(
                id,
                nameType,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameType) {

        this.nameType = Guard.maxLength(Guard.notBlank(nameType, "nameType"), 50, "nameType");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new MessageTypeUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new MessageTypeDeletedEvent(this.id, LocalDateTime.now()));
    }

    public MessageTypeId id() {
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
