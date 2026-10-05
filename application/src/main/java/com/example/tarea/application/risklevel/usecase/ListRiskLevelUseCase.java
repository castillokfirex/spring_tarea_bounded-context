package com.example.tarea.application.risklevel.usecase;

import java.util.List;

import com.example.tarea.application.risklevel.dto.RiskLevelResponse;
import com.example.tarea.domain.risklevel.port.repository.RiskLevelRepository;

public class ListRiskLevelUseCase {

    private final RiskLevelRepository repository;

    public ListRiskLevelUseCase(RiskLevelRepository repository) {
        this.repository = repository;
    }

    public List<RiskLevelResponse> execute() {
        return repository.findAll()
                .stream()
                .map(RiskLevelResponse::fromDomain)
                .toList();
    }
}
