package com.example.tarea.application.treatmentgoal.usecase;

import com.example.tarea.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.example.tarea.domain.treatmentgoal.exception.TreatmentGoalNotFoundException;
import com.example.tarea.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.example.tarea.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class GetTreatmentGoalByIdUseCase {

    private final TreatmentGoalRepository repository;

    public GetTreatmentGoalByIdUseCase(TreatmentGoalRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalResponse execute(TreatmentGoalId id) {
        return repository.findById(id)
                .map(TreatmentGoalResponse::fromDomain)
                .orElseThrow(() -> new TreatmentGoalNotFoundException(id));
    }
}
