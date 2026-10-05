package com.example.tarea.application.sendertype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.sendertype.model.aggregate.SenderType;

public record SenderTypeResponse(
        UUID id,
        String nameType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static SenderTypeResponse fromDomain(SenderType aggregate) {
        return new SenderTypeResponse(
                aggregate.id().value(),
                aggregate.nameType(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
