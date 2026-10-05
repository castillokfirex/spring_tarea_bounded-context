package com.example.tarea.application.chatconversation.usecase;

import java.util.List;

import com.example.tarea.application.chatconversation.dto.ChatConversationResponse;
import com.example.tarea.domain.chatconversation.port.repository.ChatConversationRepository;

public class ListChatConversationUseCase {

    private final ChatConversationRepository repository;

    public ListChatConversationUseCase(ChatConversationRepository repository) {
        this.repository = repository;
    }

    public List<ChatConversationResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatConversationResponse::fromDomain)
                .toList();
    }
}
