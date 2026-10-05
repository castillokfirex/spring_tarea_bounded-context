package com.example.tarea.application.clinicalrecordstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;

public record ClinicalRecordStatusResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ClinicalRecordStatusResponse fromDomain(ClinicalRecordStatus aggregate) {
        return new ClinicalRecordStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
