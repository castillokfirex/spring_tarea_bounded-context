package com.example.tarea.application.diagnosticsystem.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.diagnosticsystem.command.UpdateDiagnosticSystemCommand;
import com.example.tarea.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.example.tarea.domain.diagnosticsystem.exception.DiagnosticSystemNotFoundException;
import com.example.tarea.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.example.tarea.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class UpdateDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateDiagnosticSystemUseCase(DiagnosticSystemRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public DiagnosticSystemResponse execute(UpdateDiagnosticSystemCommand command) {

        DiagnosticSystem diagnosticSystem = repository.findById(command.id())
                .orElseThrow(() -> new DiagnosticSystemNotFoundException(command.id()));

        diagnosticSystem.update(
                command.code(),
                command.name(),
                command.active(),
                command.version());

        if (repository.existsByCodeAndIdNot(diagnosticSystem.code(), diagnosticSystem.id())) {
            throw new ConflictApplicationException(
                    "A DiagnosticSystem with the same code already exists");
        }

        DiagnosticSystem saved = repository.save(diagnosticSystem);

        eventPublisher.publish(diagnosticSystem.domainEvents());
        diagnosticSystem.clearDomainEvents();

        return DiagnosticSystemResponse.fromDomain(saved);
    }
}
