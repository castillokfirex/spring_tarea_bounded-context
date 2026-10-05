package com.example.tarea.application.risklevel.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.risklevel.exception.RiskLevelNotFoundException;
import com.example.tarea.domain.risklevel.model.aggregate.RiskLevel;
import com.example.tarea.domain.risklevel.model.valueobject.RiskLevelId;
import com.example.tarea.domain.risklevel.port.repository.RiskLevelRepository;

public class DeleteRiskLevelUseCase {

    private final RiskLevelRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteRiskLevelUseCase(RiskLevelRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(RiskLevelId id) {

        RiskLevel riskLevel = repository.findById(id)
                .orElseThrow(() -> new RiskLevelNotFoundException(id));

        riskLevel.delete();
        repository.delete(riskLevel);

        eventPublisher.publish(riskLevel.domainEvents());
        riskLevel.clearDomainEvents();
    }
}
