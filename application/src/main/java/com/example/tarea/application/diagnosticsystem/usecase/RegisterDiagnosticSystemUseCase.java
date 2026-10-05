package com.example.tarea.application.diagnosticsystem.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.diagnosticsystem.command.RegisterDiagnosticSystemCommand;
import com.example.tarea.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.example.tarea.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.example.tarea.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class RegisterDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterDiagnosticSystemUseCase(DiagnosticSystemRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public DiagnosticSystemResponse execute(RegisterDiagnosticSystemCommand command) {

        DiagnosticSystem diagnosticSystem = DiagnosticSystem.register(
                command.code(),
                command.name(),
                command.active(),
                command.version());

        if (repository.existsByCode(diagnosticSystem.code())) {
            throw new ConflictApplicationException(
                    "A DiagnosticSystem with the same code already exists");
        }

        DiagnosticSystem saved = repository.save(diagnosticSystem);

        eventPublisher.publish(diagnosticSystem.domainEvents());
        diagnosticSystem.clearDomainEvents();

        return DiagnosticSystemResponse.fromDomain(saved);
    }
}
