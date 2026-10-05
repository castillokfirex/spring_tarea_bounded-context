package com.example.tarea.application.chatescalation.usecase;

import com.example.tarea.application.chatescalation.command.UpdateChatEscalationCommand;
import com.example.tarea.application.chatescalation.dto.ChatEscalationResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalation.exception.ChatEscalationNotFoundException;
import com.example.tarea.domain.chatescalation.model.aggregate.ChatEscalation;
import com.example.tarea.domain.chatescalation.port.repository.ChatEscalationRepository;

public class UpdateChatEscalationUseCase {

    private final ChatEscalationRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateChatEscalationUseCase(ChatEscalationRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatEscalationResponse execute(UpdateChatEscalationCommand command) {

        ChatEscalation chatEscalation = repository.findById(command.id())
                .orElseThrow(() -> new ChatEscalationNotFoundException(command.id()));

        chatEscalation.update(
                command.conversationId(),
                command.statusId(),
                command.fromAi(),
                command.reason());

        ChatEscalation saved = repository.save(chatEscalation);

        eventPublisher.publish(chatEscalation.domainEvents());
        chatEscalation.clearDomainEvents();

        return ChatEscalationResponse.fromDomain(saved);
    }
}
