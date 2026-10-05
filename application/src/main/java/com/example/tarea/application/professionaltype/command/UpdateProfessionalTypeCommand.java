package com.example.tarea.application.professionaltype.command;

import com.example.tarea.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public record UpdateProfessionalTypeCommand(
        ProfessionalTypeId id,
        String name
) {
}
