package com.example.tarea.application.professionalstudy.command;

import java.util.UUID;

public record RegisterProfessionalStudyCommand(
        UUID studyId,
        UUID professionalId,
        String title,
        String university,
        Boolean isValid,
        String resolutionNumber,
        UUID countryId
) {
}
