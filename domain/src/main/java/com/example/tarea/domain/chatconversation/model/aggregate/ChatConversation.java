package com.example.tarea.domain.chatconversation.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatconversation.event.ChatConversationDeletedEvent;
import com.example.tarea.domain.chatconversation.event.ChatConversationRegisteredEvent;
import com.example.tarea.domain.chatconversation.event.ChatConversationUpdatedEvent;
import com.example.tarea.domain.chatconversation.model.valueobject.ChatConversationId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatconversation</b> (tabla <code>chat_conversations</code>).
 */
public class ChatConversation extends AggregateRoot {

    private final ChatConversationId id;
    private UUID conversationStatusId;
    private UUID priorityId;
    private LocalDateTime lastMessageAt;
    private Boolean closed;
    private LocalDateTime closedAt;
    private UUID closedBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatConversation(
            ChatConversationId id,
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.conversationStatusId = Guard.notNull(conversationStatusId, "conversationStatusId");
        this.priorityId = Guard.notNull(priorityId, "priorityId");
        this.lastMessageAt = lastMessageAt;
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatConversation register(
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy) {

        LocalDateTime now = LocalDateTime.now();
        ChatConversation aggregate = new ChatConversation(
                ChatConversationId.generate(),
                conversationStatusId,
                priorityId,
                lastMessageAt,
                closed,
                closedAt,
                closedBy,
                now,
                now);

        aggregate.recordEvent(new ChatConversationRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatConversation restore(
            ChatConversationId id,
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatConversation(
                id,
                conversationStatusId,
                priorityId,
                lastMessageAt,
                closed,
                closedAt,
                closedBy,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy) {

        this.conversationStatusId = Guard.notNull(conversationStatusId, "conversationStatusId");
        this.priorityId = Guard.notNull(priorityId, "priorityId");
        this.lastMessageAt = lastMessageAt;
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ChatConversationUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatConversationDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatConversationId id() {
        return id;
    }

    public UUID conversationStatusId() {
        return conversationStatusId;
    }

    public UUID priorityId() {
        return priorityId;
    }

    public LocalDateTime lastMessageAt() {
        return lastMessageAt;
    }

    public Boolean closed() {
        return closed;
    }

    public LocalDateTime closedAt() {
        return closedAt;
    }

    public UUID closedBy() {
        return closedBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
