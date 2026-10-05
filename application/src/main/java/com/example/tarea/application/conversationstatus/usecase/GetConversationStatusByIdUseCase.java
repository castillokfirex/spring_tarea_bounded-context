package com.example.tarea.application.conversationstatus.usecase;

import com.example.tarea.application.conversationstatus.dto.ConversationStatusResponse;
import com.example.tarea.domain.conversationstatus.exception.ConversationStatusNotFoundException;
import com.example.tarea.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.example.tarea.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class GetConversationStatusByIdUseCase {

    private final ConversationStatusRepository repository;

    public GetConversationStatusByIdUseCase(ConversationStatusRepository repository) {
        this.repository = repository;
    }

    public ConversationStatusResponse execute(ConversationStatusId id) {
        return repository.findById(id)
                .map(ConversationStatusResponse::fromDomain)
                .orElseThrow(() -> new ConversationStatusNotFoundException(id));
    }
}
