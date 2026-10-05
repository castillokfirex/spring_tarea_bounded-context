package com.example.tarea.application.patient.command;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterPatientCommand(
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        LocalDate birthDate,
        UUID biologicalSexId,
        UUID genderIdentity,
        String email,
        String phone,
        String address,
        Boolean active,
        UUID createdBy,
        UUID updatedBy,
        UUID cityId
) {
}
