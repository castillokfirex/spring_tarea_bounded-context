package com.example.tarea.domain.chatconversationaisetting.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatconversationaisetting.event.ChatConversationAiSettingDeletedEvent;
import com.example.tarea.domain.chatconversationaisetting.event.ChatConversationAiSettingRegisteredEvent;
import com.example.tarea.domain.chatconversationaisetting.event.ChatConversationAiSettingUpdatedEvent;
import com.example.tarea.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatconversationaisetting</b> (tabla <code>chat_conversation_ai_settings</code>).
 */
public class ChatConversationAiSetting extends AggregateRoot {

    private final ChatConversationAiSettingId id;
    private UUID conversationId;
    private Boolean aiEnabled;
    private UUID defaultModelId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatConversationAiSetting(
            ChatConversationAiSettingId id,
            UUID conversationId,
            Boolean aiEnabled,
            UUID defaultModelId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.aiEnabled = Guard.notNull(aiEnabled, "aiEnabled");
        this.defaultModelId = Guard.notNull(defaultModelId, "defaultModelId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatConversationAiSetting register(
            UUID conversationId,
            Boolean aiEnabled,
            UUID defaultModelId) {

        LocalDateTime now = LocalDateTime.now();
        ChatConversationAiSetting aggregate = new ChatConversationAiSetting(
                ChatConversationAiSettingId.generate(),
                conversationId,
                aiEnabled,
                defaultModelId,
                now,
                now);

        aggregate.recordEvent(new ChatConversationAiSettingRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatConversationAiSetting restore(
            ChatConversationAiSettingId id,
            UUID conversationId,
            Boolean aiEnabled,
            UUID defaultModelId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatConversationAiSetting(
                id,
                conversationId,
                aiEnabled,
                defaultModelId,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID conversationId,
            Boolean aiEnabled,
            UUID defaultModelId) {

        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.aiEnabled = Guard.notNull(aiEnabled, "aiEnabled");
        this.defaultModelId = Guard.notNull(defaultModelId, "defaultModelId");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ChatConversationAiSettingUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatConversationAiSettingDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatConversationAiSettingId id() {
        return id;
    }

    public UUID conversationId() {
        return conversationId;
    }

    public Boolean aiEnabled() {
        return aiEnabled;
    }

    public UUID defaultModelId() {
        return defaultModelId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
