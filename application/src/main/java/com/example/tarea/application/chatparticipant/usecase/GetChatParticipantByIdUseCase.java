package com.example.tarea.application.chatparticipant.usecase;

import com.example.tarea.application.chatparticipant.dto.ChatParticipantResponse;
import com.example.tarea.domain.chatparticipant.exception.ChatParticipantNotFoundException;
import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.example.tarea.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class GetChatParticipantByIdUseCase {

    private final ChatParticipantRepository repository;

    public GetChatParticipantByIdUseCase(ChatParticipantRepository repository) {
        this.repository = repository;
    }

    public ChatParticipantResponse execute(ChatParticipantId id) {
        return repository.findById(id)
                .map(ChatParticipantResponse::fromDomain)
                .orElseThrow(() -> new ChatParticipantNotFoundException(id));
    }
}
