package com.example.tarea.infrastructure.riskassessment.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateRiskAssessmentRequest(
        @NotNull UUID encounterId,
        @NotNull UUID riskLevelId,
        @NotNull Boolean suicidalIdeation,
        @NotNull Boolean suicidePlan,
        @NotNull Boolean suicideIntent,
        @NotNull Boolean selfHarm,
        @NotNull Boolean harmToOthers,
        @NotBlank String riskFactors,
        @NotBlank String protectiveFactors,
        @NotBlank String clinicalActions,
        @NotBlank String observations,
        LocalDateTime assessedAt,
        @NotNull UUID assessedBy
) {
}
