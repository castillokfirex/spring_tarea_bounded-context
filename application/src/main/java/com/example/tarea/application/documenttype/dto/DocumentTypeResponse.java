package com.example.tarea.application.documenttype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.documenttype.model.aggregate.DocumentType;

public record DocumentTypeResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static DocumentTypeResponse fromDomain(DocumentType aggregate) {
        return new DocumentTypeResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
