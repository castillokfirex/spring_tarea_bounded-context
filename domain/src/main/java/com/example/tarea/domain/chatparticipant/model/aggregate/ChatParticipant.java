package com.example.tarea.domain.chatparticipant.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatparticipant.event.ChatParticipantDeletedEvent;
import com.example.tarea.domain.chatparticipant.event.ChatParticipantRegisteredEvent;
import com.example.tarea.domain.chatparticipant.event.ChatParticipantUpdatedEvent;
import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatparticipant</b> (tabla <code>chat_participants</code>).
 */
public class ChatParticipant extends AggregateRoot {

    private final ChatParticipantId id;
    private UUID conversationId;
    private UUID participantTypeId;
    private UUID patientId;
    private UUID professionalId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatParticipant(
            ChatParticipantId id,
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.participantTypeId = Guard.notNull(participantTypeId, "participantTypeId");
        this.patientId = patientId;
        this.professionalId = professionalId;
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatParticipant register(
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId) {

        LocalDateTime now = LocalDateTime.now();
        ChatParticipant aggregate = new ChatParticipant(
                ChatParticipantId.generate(),
                conversationId,
                participantTypeId,
                patientId,
                professionalId,
                now,
                now);

        aggregate.recordEvent(new ChatParticipantRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatParticipant restore(
            ChatParticipantId id,
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatParticipant(
                id,
                conversationId,
                participantTypeId,
                patientId,
                professionalId,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId) {

        this.conversationId = Guard.notNull(conversationId, "conversationId");
        this.participantTypeId = Guard.notNull(participantTypeId, "participantTypeId");
        this.patientId = patientId;
        this.professionalId = professionalId;
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ChatParticipantUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatParticipantDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatParticipantId id() {
        return id;
    }

    public UUID conversationId() {
        return conversationId;
    }

    public UUID participantTypeId() {
        return participantTypeId;
    }

    public UUID patientId() {
        return patientId;
    }

    public UUID professionalId() {
        return professionalId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
