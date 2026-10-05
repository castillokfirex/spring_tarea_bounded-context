package com.example.tarea.application.diagnosticsystem.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.diagnosticsystem.exception.DiagnosticSystemNotFoundException;
import com.example.tarea.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.example.tarea.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.example.tarea.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class DeleteDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteDiagnosticSystemUseCase(DiagnosticSystemRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(DiagnosticSystemId id) {

        DiagnosticSystem diagnosticSystem = repository.findById(id)
                .orElseThrow(() -> new DiagnosticSystemNotFoundException(id));

        diagnosticSystem.delete();
        repository.delete(diagnosticSystem);

        eventPublisher.publish(diagnosticSystem.domainEvents());
        diagnosticSystem.clearDomainEvents();
    }
}
