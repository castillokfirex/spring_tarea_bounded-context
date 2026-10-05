package com.example.tarea.application.professional.command;

import java.util.UUID;

import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;

public record UpdateProfessionalCommand(
        ProfessionalId id,
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        UUID professionalType,
        String licenseNumber,
        Boolean active,
        UUID cityId
) {
}
