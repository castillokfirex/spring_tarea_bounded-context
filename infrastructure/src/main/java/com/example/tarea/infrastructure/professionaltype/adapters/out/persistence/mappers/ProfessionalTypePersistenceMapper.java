package com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.mappers;

import com.example.tarea.domain.professionaltype.model.aggregate.ProfessionalType;
import com.example.tarea.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;

public class ProfessionalTypePersistenceMapper {

    public ProfessionalTypeJpaEntity toJpa(ProfessionalType domain) {

        if (domain == null) {
            return null;
        }

        ProfessionalTypeJpaEntity jpa = new ProfessionalTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setName(domain.name());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ProfessionalType toDomain(ProfessionalTypeJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ProfessionalType.restore(
                new ProfessionalTypeId(jpa.getId()),
                jpa.getName(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
