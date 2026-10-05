package com.example.tarea.application.diagnosticsystem.command;

public record RegisterDiagnosticSystemCommand(
        String code,
        String name,
        Boolean active,
        String version
) {
}
