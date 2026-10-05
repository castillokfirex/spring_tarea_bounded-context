package com.example.tarea.domain.conversationstatus.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.conversationstatus.event.ConversationStatusDeletedEvent;
import com.example.tarea.domain.conversationstatus.event.ConversationStatusRegisteredEvent;
import com.example.tarea.domain.conversationstatus.event.ConversationStatusUpdatedEvent;
import com.example.tarea.domain.conversationstatus.model.valueobject.ConversationStatusId;

/**
 * Aggregate root del bounded context <b>conversationstatus</b> (tabla <code>conversations_statuses</code>).
 */
public class ConversationStatus extends AggregateRoot {

    private final ConversationStatusId id;
    private String nameStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ConversationStatus(
            ConversationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.nameStatus = Guard.maxLength(Guard.notBlank(nameStatus, "nameStatus"), 50, "nameStatus");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ConversationStatus register(
            String nameStatus) {

        LocalDateTime now = LocalDateTime.now();
        ConversationStatus aggregate = new ConversationStatus(
                ConversationStatusId.generate(),
                nameStatus,
                now,
                now);

        aggregate.recordEvent(new ConversationStatusRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ConversationStatus restore(
            ConversationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ConversationStatus(
                id,
                nameStatus,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameStatus) {

        this.nameStatus = Guard.maxLength(Guard.notBlank(nameStatus, "nameStatus"), 50, "nameStatus");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ConversationStatusUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ConversationStatusDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ConversationStatusId id() {
        return id;
    }

    public String nameStatus() {
        return nameStatus;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
