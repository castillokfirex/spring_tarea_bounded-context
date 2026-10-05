package com.example.tarea.domain.chatescalationstatushistory.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryDeletedEvent;
import com.example.tarea.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryRegisteredEvent;
import com.example.tarea.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryUpdatedEvent;
import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatescalationstatushistory</b> (tabla <code>chat_escalation_status_history</code>).
 */
public class ChatEscalationStatusHistory extends AggregateRoot {

    private final ChatEscalationStatusHistoryId id;
    private UUID escalationId;
    private UUID escalationStatusId;
    private final LocalDateTime createdAt;
    private LocalDateTime changedAt;

    private ChatEscalationStatusHistory(
            ChatEscalationStatusHistoryId id,
            UUID escalationId,
            UUID escalationStatusId,
            LocalDateTime createdAt,
            LocalDateTime changedAt) {
        this.id = Guard.notNull(id, "id");
        this.escalationId = Guard.notNull(escalationId, "escalationId");
        this.escalationStatusId = Guard.notNull(escalationStatusId, "escalationStatusId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.changedAt = Guard.notNull(changedAt, "changedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatEscalationStatusHistory register(
            UUID escalationId,
            UUID escalationStatusId,
            LocalDateTime changedAt) {

        LocalDateTime now = LocalDateTime.now();
        ChatEscalationStatusHistory aggregate = new ChatEscalationStatusHistory(
                ChatEscalationStatusHistoryId.generate(),
                escalationId,
                escalationStatusId,
                now,
                changedAt);

        aggregate.recordEvent(new ChatEscalationStatusHistoryRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatEscalationStatusHistory restore(
            ChatEscalationStatusHistoryId id,
            UUID escalationId,
            UUID escalationStatusId,
            LocalDateTime createdAt,
            LocalDateTime changedAt) {
        return new ChatEscalationStatusHistory(
                id,
                escalationId,
                escalationStatusId,
                createdAt,
                changedAt);
    }

    public void update(
            UUID escalationId,
            UUID escalationStatusId,
            LocalDateTime changedAt) {

        this.escalationId = Guard.notNull(escalationId, "escalationId");
        this.escalationStatusId = Guard.notNull(escalationStatusId, "escalationStatusId");
        this.changedAt = Guard.notNull(changedAt, "changedAt");

        recordEvent(new ChatEscalationStatusHistoryUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatEscalationStatusHistoryDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatEscalationStatusHistoryId id() {
        return id;
    }

    public UUID escalationId() {
        return escalationId;
    }

    public UUID escalationStatusId() {
        return escalationStatusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime changedAt() {
        return changedAt;
    }
}
