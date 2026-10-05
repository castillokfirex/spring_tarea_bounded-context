package com.example.tarea.application.clinicalrecordstatus.command;

public record RegisterClinicalRecordStatusCommand(
        String code,
        String name,
        Boolean active
) {
}
