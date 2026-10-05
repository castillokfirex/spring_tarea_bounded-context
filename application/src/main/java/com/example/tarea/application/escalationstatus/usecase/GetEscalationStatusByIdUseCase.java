package com.example.tarea.application.escalationstatus.usecase;

import com.example.tarea.application.escalationstatus.dto.EscalationStatusResponse;
import com.example.tarea.domain.escalationstatus.exception.EscalationStatusNotFoundException;
import com.example.tarea.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.example.tarea.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class GetEscalationStatusByIdUseCase {

    private final EscalationStatusRepository repository;

    public GetEscalationStatusByIdUseCase(EscalationStatusRepository repository) {
        this.repository = repository;
    }

    public EscalationStatusResponse execute(EscalationStatusId id) {
        return repository.findById(id)
                .map(EscalationStatusResponse::fromDomain)
                .orElseThrow(() -> new EscalationStatusNotFoundException(id));
    }
}
