package com.example.tarea.domain.chatescalationassignment.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatescalationassignment.event.ChatEscalationAssignmentDeletedEvent;
import com.example.tarea.domain.chatescalationassignment.event.ChatEscalationAssignmentRegisteredEvent;
import com.example.tarea.domain.chatescalationassignment.event.ChatEscalationAssignmentUpdatedEvent;
import com.example.tarea.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>chatescalationassignment</b> (tabla <code>chat_escalation_assignments</code>).
 */
public class ChatEscalationAssignment extends AggregateRoot {

    private final ChatEscalationAssignmentId id;
    private UUID escalationId;
    private UUID professionalId;
    private LocalDateTime assignedAt;

    private ChatEscalationAssignment(
            ChatEscalationAssignmentId id,
            UUID escalationId,
            UUID professionalId,
            LocalDateTime assignedAt) {
        this.id = Guard.notNull(id, "id");
        this.escalationId = Guard.notNull(escalationId, "escalationId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.assignedAt = Guard.notNull(assignedAt, "assignedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ChatEscalationAssignment register(
            UUID escalationId,
            UUID professionalId,
            LocalDateTime assignedAt) {

        LocalDateTime now = LocalDateTime.now();
        ChatEscalationAssignment aggregate = new ChatEscalationAssignment(
                ChatEscalationAssignmentId.generate(),
                escalationId,
                professionalId,
                assignedAt != null ? assignedAt : now);

        aggregate.recordEvent(new ChatEscalationAssignmentRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ChatEscalationAssignment restore(
            ChatEscalationAssignmentId id,
            UUID escalationId,
            UUID professionalId,
            LocalDateTime assignedAt) {
        return new ChatEscalationAssignment(
                id,
                escalationId,
                professionalId,
                assignedAt);
    }

    public void update(
            UUID escalationId,
            UUID professionalId,
            LocalDateTime assignedAt) {

        this.escalationId = Guard.notNull(escalationId, "escalationId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.assignedAt = assignedAt != null ? assignedAt : this.assignedAt;

        recordEvent(new ChatEscalationAssignmentUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ChatEscalationAssignmentDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ChatEscalationAssignmentId id() {
        return id;
    }

    public UUID escalationId() {
        return escalationId;
    }

    public UUID professionalId() {
        return professionalId;
    }

    public LocalDateTime assignedAt() {
        return assignedAt;
    }
}
