package com.example.tarea.domain.chatairunerror.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatairunerror.event.ChatAiRunErrorDeletedEvent;
import com.example.tarea.domain.chatairunerror.event.ChatAiRunErrorRegisteredEvent;
import com.example.tarea.domain.chatairunerror.event.ChatAiRunErrorUpdatedEvent;
import com.example.tarea.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatairunerror</b> (tabla <code>chat_ai_run_errors</code>).
 */
public class ChatAiRunError extends AggregateRoot {

    private final ChatAiRunErrorId id;
    private UUID aiRunId;
    private String errorMessage;
    private String errorCode;
    private String providerErrorId;
    private final LocalDateTime createdAt;

    private ChatAiRunError(
            ChatAiRunErrorId id,
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId,
            LocalDateTime createdAt) {
        this.id = Guard.notNull(id, "id");
        this.aiRunId = Guard.notNull(aiRunId, "aiRunId");
        this.errorMessage = Guard.notBlank(errorMessage, "errorMessage");
        this.errorCode = Guard.maxLength(Guard.notBlank(errorCode, "errorCode"), 80, "errorCode");
        this.providerErrorId = Guard.maxLength(Guard.notBlank(providerErrorId, "providerErrorId"), 120, "providerErrorId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatAiRunError register(
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId) {

        LocalDateTime now = LocalDateTime.now();
        ChatAiRunError aggregate = new ChatAiRunError(
                ChatAiRunErrorId.generate(),
                aiRunId,
                errorMessage,
                errorCode,
                providerErrorId,
                now);

        aggregate.recordEvent(new ChatAiRunErrorRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatAiRunError restore(
            ChatAiRunErrorId id,
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId,
            LocalDateTime createdAt) {
        return new ChatAiRunError(
                id,
                aiRunId,
                errorMessage,
                errorCode,
                providerErrorId,
                createdAt);
    }

    public void update(
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId) {

        this.aiRunId = Guard.notNull(aiRunId, "aiRunId");
        this.errorMessage = Guard.notBlank(errorMessage, "errorMessage");
        this.errorCode = Guard.maxLength(Guard.notBlank(errorCode, "errorCode"), 80, "errorCode");
        this.providerErrorId = Guard.maxLength(Guard.notBlank(providerErrorId, "providerErrorId"), 120, "providerErrorId");

        recordEvent(new ChatAiRunErrorUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatAiRunErrorDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatAiRunErrorId id() {
        return id;
    }

    public UUID aiRunId() {
        return aiRunId;
    }

    public String errorMessage() {
        return errorMessage;
    }

    public String errorCode() {
        return errorCode;
    }

    public String providerErrorId() {
        return providerErrorId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
