package com.example.tarea.application.chatescalationassignment.usecase;

import com.example.tarea.application.chatescalationassignment.command.UpdateChatEscalationAssignmentCommand;
import com.example.tarea.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundException;
import com.example.tarea.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.example.tarea.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class UpdateChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatEscalationAssignmentResponse execute(UpdateChatEscalationAssignmentCommand command) {

        ChatEscalationAssignment chatEscalationAssignment = repository.findById(command.id())
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundException(command.id()));

        chatEscalationAssignment.update(
                command.escalationId(),
                command.professionalId(),
                command.assignedAt());

        ChatEscalationAssignment saved = repository.save(chatEscalationAssignment);

        eventPublisher.publish(chatEscalationAssignment.domainEvents());
        chatEscalationAssignment.clearDomainEvents();

        return ChatEscalationAssignmentResponse.fromDomain(saved);
    }
}
