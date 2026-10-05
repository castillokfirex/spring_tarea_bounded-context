package com.example.tarea.application.professionalstudy.command;

import java.util.UUID;

import com.example.tarea.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public record UpdateProfessionalStudyCommand(
        ProfessionalStudyId id,
        UUID studyId,
        UUID professionalId,
        String title,
        String university,
        Boolean isValid,
        String resolutionNumber,
        UUID countryId
) {
}
