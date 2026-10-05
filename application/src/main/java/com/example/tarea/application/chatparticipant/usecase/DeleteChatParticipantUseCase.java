package com.example.tarea.application.chatparticipant.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatparticipant.exception.ChatParticipantNotFoundException;
import com.example.tarea.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.example.tarea.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class DeleteChatParticipantUseCase {

    private final ChatParticipantRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatParticipantUseCase(ChatParticipantRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatParticipantId id) {

        ChatParticipant chatParticipant = repository.findById(id)
                .orElseThrow(() -> new ChatParticipantNotFoundException(id));

        chatParticipant.delete();
        repository.delete(chatParticipant);

        eventPublisher.publish(chatParticipant.domainEvents());
        chatParticipant.clearDomainEvents();
    }
}
