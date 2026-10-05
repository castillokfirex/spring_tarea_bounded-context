package com.example.tarea.application.clinicalrecord.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public record UpdateClinicalRecordCommand(
        ClinicalRecordId id,
        UUID patientId,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        UUID statusId
) {
}
