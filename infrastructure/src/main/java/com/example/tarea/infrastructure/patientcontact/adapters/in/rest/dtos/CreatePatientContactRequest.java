package com.example.tarea.infrastructure.patientcontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreatePatientContactRequest(
        @NotNull UUID contactId,
        @NotNull UUID patientId,
        @NotNull Boolean isPrimaryContact,
        @NotNull Boolean isEmergencyContact,
        @NotNull UUID relationshipTypeId
) {
}
