package com.example.tarea.application.treatmentgoalstatus.usecase;

import com.example.tarea.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.example.tarea.domain.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundException;
import com.example.tarea.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.example.tarea.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class GetTreatmentGoalStatusByIdUseCase {

    private final TreatmentGoalStatusRepository repository;

    public GetTreatmentGoalStatusByIdUseCase(TreatmentGoalStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalStatusResponse execute(TreatmentGoalStatusId id) {
        return repository.findById(id)
                .map(TreatmentGoalStatusResponse::fromDomain)
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundException(id));
    }
}
