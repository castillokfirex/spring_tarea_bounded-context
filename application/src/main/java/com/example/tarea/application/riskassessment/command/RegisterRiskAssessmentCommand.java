package com.example.tarea.application.riskassessment.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterRiskAssessmentCommand(
        UUID encounterId,
        UUID riskLevelId,
        Boolean suicidalIdeation,
        Boolean suicidePlan,
        Boolean suicideIntent,
        Boolean selfHarm,
        Boolean harmToOthers,
        String riskFactors,
        String protectiveFactors,
        String clinicalActions,
        String observations,
        LocalDateTime assessedAt,
        UUID assessedBy
) {
}
