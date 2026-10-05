package com.example.tarea.infrastructure.encountertype.adapters.out.persistence.mappers;

import com.example.tarea.domain.encountertype.model.aggregate.EncounterType;
import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;
import com.example.tarea.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;

public class EncounterTypePersistenceMapper {

    public EncounterTypeJpaEntity toJpa(EncounterType domain) {

        if (domain == null) {
            return null;
        }

        EncounterTypeJpaEntity jpa = new EncounterTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public EncounterType toDomain(EncounterTypeJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return EncounterType.restore(
                new EncounterTypeId(jpa.getId()),
                jpa.getCode(),
                jpa.getName(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
