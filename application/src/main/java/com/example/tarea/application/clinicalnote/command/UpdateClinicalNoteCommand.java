package com.example.tarea.application.clinicalnote.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public record UpdateClinicalNoteCommand(
        ClinicalNoteId id,
        UUID encounterId,
        UUID professionalId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes,
        LocalDateTime signedAt
) {
}
