package com.example.tarea.application.risklevel.command;

public record RegisterRiskLevelCommand(
        String code,
        String name,
        Boolean active,
        Integer severity
) {
}
