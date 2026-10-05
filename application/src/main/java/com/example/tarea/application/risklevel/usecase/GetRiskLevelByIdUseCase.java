package com.example.tarea.application.risklevel.usecase;

import com.example.tarea.application.risklevel.dto.RiskLevelResponse;
import com.example.tarea.domain.risklevel.exception.RiskLevelNotFoundException;
import com.example.tarea.domain.risklevel.model.valueobject.RiskLevelId;
import com.example.tarea.domain.risklevel.port.repository.RiskLevelRepository;

public class GetRiskLevelByIdUseCase {

    private final RiskLevelRepository repository;

    public GetRiskLevelByIdUseCase(RiskLevelRepository repository) {
        this.repository = repository;
    }

    public RiskLevelResponse execute(RiskLevelId id) {
        return repository.findById(id)
                .map(RiskLevelResponse::fromDomain)
                .orElseThrow(() -> new RiskLevelNotFoundException(id));
    }
}
