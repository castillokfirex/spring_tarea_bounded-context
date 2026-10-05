package com.example.tarea.application.patient.command;

import java.time.LocalDate;
import java.util.UUID;

import com.example.tarea.domain.patient.model.valueobject.PatientId;

public record UpdatePatientCommand(
        PatientId id,
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
        UUID updatedBy,
        UUID cityId
) {
}
