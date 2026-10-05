package com.example.tarea.application.chatmessage.usecase;

import com.example.tarea.application.chatmessage.command.RegisterChatMessageCommand;
import com.example.tarea.application.chatmessage.dto.ChatMessageResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatmessage.model.aggregate.ChatMessage;
import com.example.tarea.domain.chatmessage.port.repository.ChatMessageRepository;

public class RegisterChatMessageUseCase {

    private final ChatMessageRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterChatMessageUseCase(ChatMessageRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatMessageResponse execute(RegisterChatMessageCommand command) {

        ChatMessage chatMessage = ChatMessage.register(
                command.conversationId(),
                command.messageTypeId(),
                command.participantId(),
                command.content(),
                command.metadata());

        ChatMessage saved = repository.save(chatMessage);

        eventPublisher.publish(chatMessage.domainEvents());
        chatMessage.clearDomainEvents();

        return ChatMessageResponse.fromDomain(saved);
    }
}
