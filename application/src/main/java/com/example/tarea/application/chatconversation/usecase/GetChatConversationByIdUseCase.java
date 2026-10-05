package com.example.tarea.application.chatconversation.usecase;

import com.example.tarea.application.chatconversation.dto.ChatConversationResponse;
import com.example.tarea.domain.chatconversation.exception.ChatConversationNotFoundException;
import com.example.tarea.domain.chatconversation.model.valueobject.ChatConversationId;
import com.example.tarea.domain.chatconversation.port.repository.ChatConversationRepository;

public class GetChatConversationByIdUseCase {

    private final ChatConversationRepository repository;

    public GetChatConversationByIdUseCase(ChatConversationRepository repository) {
        this.repository = repository;
    }

    public ChatConversationResponse execute(ChatConversationId id) {
        return repository.findById(id)
                .map(ChatConversationResponse::fromDomain)
                .orElseThrow(() -> new ChatConversationNotFoundException(id));
    }
}
