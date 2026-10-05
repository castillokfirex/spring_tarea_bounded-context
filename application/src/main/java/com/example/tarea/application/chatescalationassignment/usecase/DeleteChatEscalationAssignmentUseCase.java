package com.example.tarea.application.chatescalationassignment.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundException;
import com.example.tarea.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.example.tarea.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.example.tarea.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class DeleteChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatEscalationAssignmentId id) {

        ChatEscalationAssignment chatEscalationAssignment = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundException(id));

        chatEscalationAssignment.delete();
        repository.delete(chatEscalationAssignment);

        eventPublisher.publish(chatEscalationAssignment.domainEvents());
        chatEscalationAssignment.clearDomainEvents();
    }
}
