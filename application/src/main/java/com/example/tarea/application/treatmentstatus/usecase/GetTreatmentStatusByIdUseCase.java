package com.example.tarea.application.treatmentstatus.usecase;

import com.example.tarea.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.example.tarea.domain.treatmentstatus.exception.TreatmentStatusNotFoundException;
import com.example.tarea.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.example.tarea.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class GetTreatmentStatusByIdUseCase {

    private final TreatmentStatusRepository repository;

    public GetTreatmentStatusByIdUseCase(TreatmentStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentStatusResponse execute(TreatmentStatusId id) {
        return repository.findById(id)
                .map(TreatmentStatusResponse::fromDomain)
                .orElseThrow(() -> new TreatmentStatusNotFoundException(id));
    }
}
