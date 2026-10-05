package com.example.tarea.application.chatconversation.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatconversation.exception.ChatConversationNotFoundException;
import com.example.tarea.domain.chatconversation.model.aggregate.ChatConversation;
import com.example.tarea.domain.chatconversation.model.valueobject.ChatConversationId;
import com.example.tarea.domain.chatconversation.port.repository.ChatConversationRepository;

public class DeleteChatConversationUseCase {

    private final ChatConversationRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatConversationUseCase(ChatConversationRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatConversationId id) {

        ChatConversation chatConversation = repository.findById(id)
                .orElseThrow(() -> new ChatConversationNotFoundException(id));

        chatConversation.delete();
        repository.delete(chatConversation);

        eventPublisher.publish(chatConversation.domainEvents());
        chatConversation.clearDomainEvents();
    }
}
