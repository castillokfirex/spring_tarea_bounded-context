package com.example.tarea.domain.chatairun.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatairun.event.ChatAiRunDeletedEvent;
import com.example.tarea.domain.chatairun.event.ChatAiRunRegisteredEvent;
import com.example.tarea.domain.chatairun.event.ChatAiRunUpdatedEvent;
import com.example.tarea.domain.chatairun.model.valueobject.ChatAiRunId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatairun</b> (tabla <code>chat_ai_runs</code>).
 */
public class ChatAiRun extends AggregateRoot {

    private final ChatAiRunId id;
    private UUID conversationId;
    private UUID messageId;
    private UUID modelId;
    private UUID aiRunStatusId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatAiRun(
            ChatAiRunId id,
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.messageId = Guard.notNull(messageId, "messageId");
        this.modelId = Guard.notNull(modelId, "modelId");
        this.aiRunStatusId = Guard.notNull(aiRunStatusId, "aiRunStatusId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatAiRun register(
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId) {

        LocalDateTime now = LocalDateTime.now();
        ChatAiRun aggregate = new ChatAiRun(
                ChatAiRunId.generate(),
                conversationId,
                messageId,
                modelId,
                aiRunStatusId,
                now,
                now);

        aggregate.recordEvent(new ChatAiRunRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatAiRun restore(
            ChatAiRunId id,
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatAiRun(
                id,
                conversationId,
                messageId,
                modelId,
                aiRunStatusId,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId) {

        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.messageId = Guard.notNull(messageId, "messageId");
        this.modelId = Guard.notNull(modelId, "modelId");
        this.aiRunStatusId = Guard.notNull(aiRunStatusId, "aiRunStatusId");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ChatAiRunUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatAiRunDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatAiRunId id() {
        return id;
    }

    public UUID conversationId() {
        return conversationId;
    }

    public UUID messageId() {
        return messageId;
    }

    public UUID modelId() {
        return modelId;
    }

    public UUID aiRunStatusId() {
        return aiRunStatusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
