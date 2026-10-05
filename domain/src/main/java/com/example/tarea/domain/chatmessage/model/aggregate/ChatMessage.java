package com.example.tarea.domain.chatmessage.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatmessage.event.ChatMessageDeletedEvent;
import com.example.tarea.domain.chatmessage.event.ChatMessageRegisteredEvent;
import com.example.tarea.domain.chatmessage.event.ChatMessageUpdatedEvent;
import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatmessage</b> (tabla <code>chat_messages</code>).
 */
public class ChatMessage extends AggregateRoot {

    private final ChatMessageId id;
    private UUID conversationId;
    private UUID messageTypeId;
    private UUID participantId;
    private String content;
    private String metadata;
    private final LocalDateTime createdAt;

    private ChatMessage(
            ChatMessageId id,
            UUID conversationId,
            UUID messageTypeId,
            UUID participantId,
            String content,
            String metadata,
            LocalDateTime createdAt) {
        this.id = Guard.notNull(id, "id");
        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.messageTypeId = Guard.notNull(messageTypeId, "messageTypeId");
        this.participantId = Guard.notNull(participantId, "participantId");
        this.content = Guard.notBlank(content, "content");
        this.metadata = Guard.notBlank(metadata, "metadata");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatMessage register(
            UUID conversationId,
            UUID messageTypeId,
            UUID participantId,
            String content,
            String metadata) {

        LocalDateTime now = LocalDateTime.now();
        ChatMessage aggregate = new ChatMessage(
                ChatMessageId.generate(),
                conversationId,
                messageTypeId,
                participantId,
                content,
                metadata,
                now);

        aggregate.recordEvent(new ChatMessageRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatMessage restore(
            ChatMessageId id,
            UUID conversationId,
            UUID messageTypeId,
            UUID participantId,
            String content,
            String metadata,
            LocalDateTime createdAt) {
        return new ChatMessage(
                id,
                conversationId,
                messageTypeId,
                participantId,
                content,
                metadata,
                createdAt);
    }

    public void update(
            UUID conversationId,
            UUID messageTypeId,
            UUID participantId,
            String content,
            String metadata) {

        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.messageTypeId = Guard.notNull(messageTypeId, "messageTypeId");
        this.participantId = Guard.notNull(participantId, "participantId");
        this.content = Guard.notBlank(content, "content");
        this.metadata = Guard.notBlank(metadata, "metadata");

        recordEvent(new ChatMessageUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatMessageDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatMessageId id() {
        return id;
    }

    public UUID conversationId() {
        return conversationId;
    }

    public UUID messageTypeId() {
        return messageTypeId;
    }

    public UUID participantId() {
        return participantId;
    }

    public String content() {
        return content;
    }

    public String metadata() {
        return metadata;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
