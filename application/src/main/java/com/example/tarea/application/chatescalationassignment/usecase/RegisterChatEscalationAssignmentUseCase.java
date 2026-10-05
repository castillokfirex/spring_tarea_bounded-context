package com.example.tarea.application.chatescalationassignment.usecase;

import com.example.tarea.application.chatescalationassignment.command.RegisterChatEscalationAssignmentCommand;
import com.example.tarea.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.example.tarea.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class RegisterChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatEscalationAssignmentResponse execute(RegisterChatEscalationAssignmentCommand command) {

        ChatEscalationAssignment chatEscalationAssignment = ChatEscalationAssignment.register(
                command.escalationId(),
                command.professionalId(),
                command.assignedAt());

        ChatEscalationAssignment saved = repository.save(chatEscalationAssignment);

        eventPublisher.publish(chatEscalationAssignment.domainEvents());
        chatEscalationAssignment.clearDomainEvents();

        return ChatEscalationAssignmentResponse.fromDomain(saved);
    }
}
