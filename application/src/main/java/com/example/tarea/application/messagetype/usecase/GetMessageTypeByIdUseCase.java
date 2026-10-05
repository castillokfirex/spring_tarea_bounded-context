package com.example.tarea.application.messagetype.usecase;

import com.example.tarea.application.messagetype.dto.MessageTypeResponse;
import com.example.tarea.domain.messagetype.exception.MessageTypeNotFoundException;
import com.example.tarea.domain.messagetype.model.valueobject.MessageTypeId;
import com.example.tarea.domain.messagetype.port.repository.MessageTypeRepository;

public class GetMessageTypeByIdUseCase {

    private final MessageTypeRepository repository;

    public GetMessageTypeByIdUseCase(MessageTypeRepository repository) {
        this.repository = repository;
    }

    public MessageTypeResponse execute(MessageTypeId id) {
        return repository.findById(id)
                .map(MessageTypeResponse::fromDomain)
                .orElseThrow(() -> new MessageTypeNotFoundException(id));
    }
}
