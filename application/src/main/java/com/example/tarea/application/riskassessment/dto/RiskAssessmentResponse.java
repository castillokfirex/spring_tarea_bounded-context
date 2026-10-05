package com.example.tarea.application.riskassessment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.riskassessment.model.aggregate.RiskAssessment;

public record RiskAssessmentResponse(
        UUID id,
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
        UUID assessedBy) {

    public static RiskAssessmentResponse fromDomain(RiskAssessment aggregate) {
        return new RiskAssessmentResponse(
                aggregate.id().value(),
                aggregate.encounterId(),
                aggregate.riskLevelId(),
                aggregate.suicidalIdeation(),
                aggregate.suicidePlan(),
                aggregate.suicideIntent(),
                aggregate.selfHarm(),
                aggregate.harmToOthers(),
                aggregate.riskFactors(),
                aggregate.protectiveFactors(),
                aggregate.clinicalActions(),
                aggregate.observations(),
                aggregate.assessedAt(),
                aggregate.assessedBy());
    }
}
