package com.example.tarea.infrastructure.clinicalrecord.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateClinicalRecordRequest(
        @NotNull UUID patientId,
        LocalDateTime creationDate,
        @NotBlank @Size(max = 50) String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        @NotNull UUID statusId
) {
}
