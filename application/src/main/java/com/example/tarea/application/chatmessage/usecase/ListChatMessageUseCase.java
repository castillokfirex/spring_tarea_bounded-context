package com.example.tarea.application.chatmessage.usecase;

import java.util.List;

import com.example.tarea.application.chatmessage.dto.ChatMessageResponse;
import com.example.tarea.domain.chatmessage.port.repository.ChatMessageRepository;

public class ListChatMessageUseCase {

    private final ChatMessageRepository repository;

    public ListChatMessageUseCase(ChatMessageRepository repository) {
        this.repository = repository;
    }

    public List<ChatMessageResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatMessageResponse::fromDomain)
                .toList();
    }
}
