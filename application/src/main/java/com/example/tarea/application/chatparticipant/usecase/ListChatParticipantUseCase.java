package com.example.tarea.application.chatparticipant.usecase;

import java.util.List;

import com.example.tarea.application.chatparticipant.dto.ChatParticipantResponse;
import com.example.tarea.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class ListChatParticipantUseCase {

    private final ChatParticipantRepository repository;

    public ListChatParticipantUseCase(ChatParticipantRepository repository) {
        this.repository = repository;
    }

    public List<ChatParticipantResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatParticipantResponse::fromDomain)
                .toList();
    }
}
