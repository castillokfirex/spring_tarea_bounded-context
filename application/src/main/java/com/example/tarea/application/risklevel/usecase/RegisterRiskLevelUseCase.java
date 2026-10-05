package com.example.tarea.application.risklevel.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.risklevel.command.RegisterRiskLevelCommand;
import com.example.tarea.application.risklevel.dto.RiskLevelResponse;
import com.example.tarea.domain.risklevel.model.aggregate.RiskLevel;
import com.example.tarea.domain.risklevel.port.repository.RiskLevelRepository;

public class RegisterRiskLevelUseCase {

    private final RiskLevelRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterRiskLevelUseCase(RiskLevelRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public RiskLevelResponse execute(RegisterRiskLevelCommand command) {

        RiskLevel riskLevel = RiskLevel.register(
                command.code(),
                command.name(),
                command.active(),
                command.severity());

        if (repository.existsByCode(riskLevel.code())) {
            throw new ConflictApplicationException(
                    "A RiskLevel with the same code already exists");
        }

        RiskLevel saved = repository.save(riskLevel);

        eventPublisher.publish(riskLevel.domainEvents());
        riskLevel.clearDomainEvents();

        return RiskLevelResponse.fromDomain(saved);
    }
}
