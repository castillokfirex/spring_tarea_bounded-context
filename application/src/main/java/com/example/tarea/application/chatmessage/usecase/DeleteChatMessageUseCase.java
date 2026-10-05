package com.example.tarea.application.chatmessage.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatmessage.exception.ChatMessageNotFoundException;
import com.example.tarea.domain.chatmessage.model.aggregate.ChatMessage;
import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;
import com.example.tarea.domain.chatmessage.port.repository.ChatMessageRepository;

public class DeleteChatMessageUseCase {

    private final ChatMessageRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatMessageUseCase(ChatMessageRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatMessageId id) {

        ChatMessage chatMessage = repository.findById(id)
                .orElseThrow(() -> new ChatMessageNotFoundException(id));

        chatMessage.delete();
        repository.delete(chatMessage);

        eventPublisher.publish(chatMessage.domainEvents());
        chatMessage.clearDomainEvents();
    }
}
