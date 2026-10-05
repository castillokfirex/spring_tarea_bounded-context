package com.example.tarea.application.patientcontact.command;

import java.util.UUID;

import com.example.tarea.domain.patientcontact.model.valueobject.PatientContactId;

public record UpdatePatientContactCommand(
        PatientContactId id,
        UUID contactId,
        UUID patientId,
        Boolean isPrimaryContact,
        Boolean isEmergencyContact,
        UUID relationshipTypeId
) {
}
