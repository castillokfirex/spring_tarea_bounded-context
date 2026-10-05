package com.example.tarea.application.risklevel.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.risklevel.command.UpdateRiskLevelCommand;
import com.example.tarea.application.risklevel.dto.RiskLevelResponse;
import com.example.tarea.domain.risklevel.exception.RiskLevelNotFoundException;
import com.example.tarea.domain.risklevel.model.aggregate.RiskLevel;
import com.example.tarea.domain.risklevel.port.repository.RiskLevelRepository;

public class UpdateRiskLevelUseCase {

    private final RiskLevelRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateRiskLevelUseCase(RiskLevelRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public RiskLevelResponse execute(UpdateRiskLevelCommand command) {

        RiskLevel riskLevel = repository.findById(command.id())
                .orElseThrow(() -> new RiskLevelNotFoundException(command.id()));

        riskLevel.update(
                command.code(),
                command.name(),
                command.active(),
                command.severity());

        if (repository.existsByCodeAndIdNot(riskLevel.code(), riskLevel.id())) {
            throw new ConflictApplicationException(
                    "A RiskLevel with the same code already exists");
        }

        RiskLevel saved = repository.save(riskLevel);

        eventPublisher.publish(riskLevel.domainEvents());
        riskLevel.clearDomainEvents();

        return RiskLevelResponse.fromDomain(saved);
    }
}
