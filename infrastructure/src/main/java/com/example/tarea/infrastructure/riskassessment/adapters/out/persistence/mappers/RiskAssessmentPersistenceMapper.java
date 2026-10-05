package com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.mappers;

import com.example.tarea.domain.riskassessment.model.aggregate.RiskAssessment;
import com.example.tarea.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;

public class RiskAssessmentPersistenceMapper {

    public RiskAssessmentJpaEntity toJpa(RiskAssessment domain) {

        if (domain == null) {
            return null;
        }

        RiskAssessmentJpaEntity jpa = new RiskAssessmentJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEncounterId(domain.encounterId());
        jpa.setRiskLevelId(domain.riskLevelId());
        jpa.setSuicidalIdeation(domain.suicidalIdeation());
        jpa.setSuicidePlan(domain.suicidePlan());
        jpa.setSuicideIntent(domain.suicideIntent());
        jpa.setSelfHarm(domain.selfHarm());
        jpa.setHarmToOthers(domain.harmToOthers());
        jpa.setRiskFactors(domain.riskFactors());
        jpa.setProtectiveFactors(domain.protectiveFactors());
        jpa.setClinicalActions(domain.clinicalActions());
        jpa.setObservations(domain.observations());
        jpa.setAssessedAt(domain.assessedAt());
        jpa.setAssessedBy(domain.assessedBy());

        return jpa;
    }

    public RiskAssessment toDomain(RiskAssessmentJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return RiskAssessment.restore(
                new RiskAssessmentId(jpa.getId()),
                jpa.getEncounterId(),
                jpa.getRiskLevelId(),
                jpa.getSuicidalIdeation(),
                jpa.getSuicidePlan(),
                jpa.getSuicideIntent(),
                jpa.getSelfHarm(),
                jpa.getHarmToOthers(),
                jpa.getRiskFactors(),
                jpa.getProtectiveFactors(),
                jpa.getClinicalActions(),
                jpa.getObservations(),
                jpa.getAssessedAt(),
                jpa.getAssessedBy());
    }
}
