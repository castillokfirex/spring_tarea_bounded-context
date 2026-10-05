package com.example.tarea.application.sendertype.usecase;

import com.example.tarea.application.sendertype.dto.SenderTypeResponse;
import com.example.tarea.domain.sendertype.exception.SenderTypeNotFoundException;
import com.example.tarea.domain.sendertype.model.valueobject.SenderTypeId;
import com.example.tarea.domain.sendertype.port.repository.SenderTypeRepository;

public class GetSenderTypeByIdUseCase {

    private final SenderTypeRepository repository;

    public GetSenderTypeByIdUseCase(SenderTypeRepository repository) {
        this.repository = repository;
    }

    public SenderTypeResponse execute(SenderTypeId id) {
        return repository.findById(id)
                .map(SenderTypeResponse::fromDomain)
                .orElseThrow(() -> new SenderTypeNotFoundException(id));
    }
}
