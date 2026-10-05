package com.example.tarea.application.chatescalation.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalation.exception.ChatEscalationNotFoundException;
import com.example.tarea.domain.chatescalation.model.aggregate.ChatEscalation;
import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.example.tarea.domain.chatescalation.port.repository.ChatEscalationRepository;

public class DeleteChatEscalationUseCase {

    private final ChatEscalationRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatEscalationUseCase(ChatEscalationRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatEscalationId id) {

        ChatEscalation chatEscalation = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationNotFoundException(id));

        chatEscalation.delete();
        repository.delete(chatEscalation);

        eventPublisher.publish(chatEscalation.domainEvents());
        chatEscalation.clearDomainEvents();
    }
}
