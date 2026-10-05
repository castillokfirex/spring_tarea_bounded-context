package com.example.tarea.application.treatmentplan.usecase;

import com.example.tarea.application.treatmentplan.dto.TreatmentPlanResponse;
import com.example.tarea.domain.treatmentplan.exception.TreatmentPlanNotFoundException;
import com.example.tarea.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.example.tarea.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class GetTreatmentPlanByIdUseCase {

    private final TreatmentPlanRepository repository;

    public GetTreatmentPlanByIdUseCase(TreatmentPlanRepository repository) {
        this.repository = repository;
    }

    public TreatmentPlanResponse execute(TreatmentPlanId id) {
        return repository.findById(id)
                .map(TreatmentPlanResponse::fromDomain)
                .orElseThrow(() -> new TreatmentPlanNotFoundException(id));
    }
}
