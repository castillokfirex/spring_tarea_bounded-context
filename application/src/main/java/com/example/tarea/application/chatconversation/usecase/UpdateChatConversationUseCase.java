package com.example.tarea.application.chatconversation.usecase;

import com.example.tarea.application.chatconversation.command.UpdateChatConversationCommand;
import com.example.tarea.application.chatconversation.dto.ChatConversationResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatconversation.exception.ChatConversationNotFoundException;
import com.example.tarea.domain.chatconversation.model.aggregate.ChatConversation;
import com.example.tarea.domain.chatconversation.port.repository.ChatConversationRepository;

public class UpdateChatConversationUseCase {

    private final ChatConversationRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateChatConversationUseCase(ChatConversationRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatConversationResponse execute(UpdateChatConversationCommand command) {

        ChatConversation chatConversation = repository.findById(command.id())
                .orElseThrow(() -> new ChatConversationNotFoundException(command.id()));

        chatConversation.update(
                command.conversationStatusId(),
                command.priorityId(),
                command.lastMessageAt(),
                command.closed(),
                command.closedAt(),
                command.closedBy());

        ChatConversation saved = repository.save(chatConversation);

        eventPublisher.publish(chatConversation.domainEvents());
        chatConversation.clearDomainEvents();

        return ChatConversationResponse.fromDomain(saved);
    }
}
