package com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.mappers;

import com.example.tarea.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.example.tarea.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;

public class TreatmentPlanPersistenceMapper {

    public TreatmentPlanJpaEntity toJpa(TreatmentPlan domain) {

        if (domain == null) {
            return null;
        }

        TreatmentPlanJpaEntity jpa = new TreatmentPlanJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEncounterId(domain.encounterId());
        jpa.setProfessionalId(domain.professionalId());
        jpa.setTitle(domain.title());
        jpa.setDescription(domain.description());
        jpa.setStartDate(domain.startDate());
        jpa.setEndDate(domain.endDate());
        jpa.setTreatmentStatusId(domain.treatmentStatusId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public TreatmentPlan toDomain(TreatmentPlanJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return TreatmentPlan.restore(
                new TreatmentPlanId(jpa.getId()),
                jpa.getEncounterId(),
                jpa.getProfessionalId(),
                jpa.getTitle(),
                jpa.getDescription(),
                jpa.getStartDate(),
                jpa.getEndDate(),
                jpa.getTreatmentStatusId(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
