package com.example.tarea.application.patientallergy.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterPatientAllergyCommand(
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        Boolean active,
        LocalDateTime recordedAt,
        UUID recordedBy
) {
}
