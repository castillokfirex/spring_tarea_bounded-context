package com.example.tarea.domain.chatescalation.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatescalation.event.ChatEscalationDeletedEvent;
import com.example.tarea.domain.chatescalation.event.ChatEscalationRegisteredEvent;
import com.example.tarea.domain.chatescalation.event.ChatEscalationUpdatedEvent;
import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatescalation</b> (tabla <code>chat_escalations</code>).
 */
public class ChatEscalation extends AggregateRoot {

    private final ChatEscalationId id;
    private UUID conversationId;
    private UUID statusId;
    private Boolean fromAi;
    private String reason;
    private final LocalDateTime createdAt;

    private ChatEscalation(
            ChatEscalationId id,
            UUID conversationId,
            UUID statusId,
            Boolean fromAi,
            String reason,
            LocalDateTime createdAt) {
        this.id = Guard.notNull(id, "id");
        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.statusId = Guard.notNull(statusId, "statusId");
        this.fromAi = Guard.notNull(fromAi, "fromAi");
        this.reason = Guard.notBlank(reason, "reason");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatEscalation register(
            UUID conversationId,
            UUID statusId,
            Boolean fromAi,
            String reason) {

        LocalDateTime now = LocalDateTime.now();
        ChatEscalation aggregate = new ChatEscalation(
                ChatEscalationId.generate(),
                conversationId,
                statusId,
                fromAi,
                reason,
                now);

        aggregate.recordEvent(new ChatEscalationRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatEscalation restore(
            ChatEscalationId id,
            UUID conversationId,
            UUID statusId,
            Boolean fromAi,
            String reason,
            LocalDateTime createdAt) {
        return new ChatEscalation(
                id,
                conversationId,
                statusId,
                fromAi,
                reason,
                createdAt);
    }

    public void update(
            UUID conversationId,
            UUID statusId,
            Boolean fromAi,
            String reason) {

        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.statusId = Guard.notNull(statusId, "statusId");
        this.fromAi = Guard.notNull(fromAi, "fromAi");
        this.reason = Guard.notBlank(reason, "reason");

        recordEvent(new ChatEscalationUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatEscalationDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatEscalationId id() {
        return id;
    }

    public UUID conversationId() {
        return conversationId;
    }

    public UUID statusId() {
        return statusId;
    }

    public Boolean fromAi() {
        return fromAi;
    }

    public String reason() {
        return reason;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
