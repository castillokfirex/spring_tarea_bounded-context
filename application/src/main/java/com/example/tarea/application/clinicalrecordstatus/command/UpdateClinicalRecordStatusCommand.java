package com.example.tarea.application.clinicalrecordstatus.command;

import com.example.tarea.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public record UpdateClinicalRecordStatusCommand(
        ClinicalRecordStatusId id,
        String code,
        String name,
        Boolean active
) {
}
