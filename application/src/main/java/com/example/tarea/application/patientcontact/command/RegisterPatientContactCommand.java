package com.example.tarea.application.patientcontact.command;

import java.util.UUID;

public record RegisterPatientContactCommand(
        UUID contactId,
        UUID patientId,
        Boolean isPrimaryContact,
        Boolean isEmergencyContact,
        UUID relationshipTypeId
) {
}
