package com.example.tarea.application.diagnosticsystem.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;

public record DiagnosticSystemResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        String version,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static DiagnosticSystemResponse fromDomain(DiagnosticSystem aggregate) {
        return new DiagnosticSystemResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.version(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
