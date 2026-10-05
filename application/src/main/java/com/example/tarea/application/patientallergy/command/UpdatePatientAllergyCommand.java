package com.example.tarea.application.patientallergy.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.patientallergy.model.valueobject.PatientAllergyId;

public record UpdatePatientAllergyCommand(
        PatientAllergyId id,
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        Boolean active,
        LocalDateTime recordedAt,
        UUID recordedBy
) {
}
