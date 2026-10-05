package com.example.tarea.application.chatmessage.usecase;

import com.example.tarea.application.chatmessage.dto.ChatMessageResponse;
import com.example.tarea.domain.chatmessage.exception.ChatMessageNotFoundException;
import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;
import com.example.tarea.domain.chatmessage.port.repository.ChatMessageRepository;

public class GetChatMessageByIdUseCase {

    private final ChatMessageRepository repository;

    public GetChatMessageByIdUseCase(ChatMessageRepository repository) {
        this.repository = repository;
    }

    public ChatMessageResponse execute(ChatMessageId id) {
        return repository.findById(id)
                .map(ChatMessageResponse::fromDomain)
                .orElseThrow(() -> new ChatMessageNotFoundException(id));
    }
}
