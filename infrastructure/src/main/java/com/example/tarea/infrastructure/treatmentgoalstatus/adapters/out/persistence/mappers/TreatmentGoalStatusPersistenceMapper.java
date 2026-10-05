package com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.mappers;

import com.example.tarea.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.example.tarea.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;

public class TreatmentGoalStatusPersistenceMapper {

    public TreatmentGoalStatusJpaEntity toJpa(TreatmentGoalStatus domain) {

        if (domain == null) {
            return null;
        }

        TreatmentGoalStatusJpaEntity jpa = new TreatmentGoalStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public TreatmentGoalStatus toDomain(TreatmentGoalStatusJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return TreatmentGoalStatus.restore(
                new TreatmentGoalStatusId(jpa.getId()),
                jpa.getCode(),
                jpa.getName(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
