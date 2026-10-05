package com.example.tarea.application.diagnosticsystem.command;

import com.example.tarea.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public record UpdateDiagnosticSystemCommand(
        DiagnosticSystemId id,
        String code,
        String name,
        Boolean active,
        String version
) {
}
