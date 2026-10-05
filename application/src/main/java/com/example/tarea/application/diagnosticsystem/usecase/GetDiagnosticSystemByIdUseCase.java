package com.example.tarea.application.diagnosticsystem.usecase;

import com.example.tarea.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.example.tarea.domain.diagnosticsystem.exception.DiagnosticSystemNotFoundException;
import com.example.tarea.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.example.tarea.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class GetDiagnosticSystemByIdUseCase {

    private final DiagnosticSystemRepository repository;

    public GetDiagnosticSystemByIdUseCase(DiagnosticSystemRepository repository) {
        this.repository = repository;
    }

    public DiagnosticSystemResponse execute(DiagnosticSystemId id) {
        return repository.findById(id)
                .map(DiagnosticSystemResponse::fromDomain)
                .orElseThrow(() -> new DiagnosticSystemNotFoundException(id));
    }
}
