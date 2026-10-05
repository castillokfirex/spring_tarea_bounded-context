package com.example.tarea.infrastructure.risklevel.adapters.out.persistence.mappers;

import com.example.tarea.domain.risklevel.model.aggregate.RiskLevel;
import com.example.tarea.domain.risklevel.model.valueobject.RiskLevelId;
import com.example.tarea.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;

public class RiskLevelPersistenceMapper {

    public RiskLevelJpaEntity toJpa(RiskLevel domain) {

        if (domain == null) {
            return null;
        }

        RiskLevelJpaEntity jpa = new RiskLevelJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setSeverity(domain.severity());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public RiskLevel toDomain(RiskLevelJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return RiskLevel.restore(
                new RiskLevelId(jpa.getId()),
                jpa.getCode(),
                jpa.getName(),
                jpa.getActive(),
                jpa.getSeverity(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
