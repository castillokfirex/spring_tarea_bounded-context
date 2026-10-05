package com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.mappers;

import com.example.tarea.domain.medicationroute.model.aggregate.MedicationRoute;
import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;

public class MedicationRoutePersistenceMapper {

    public MedicationRouteJpaEntity toJpa(MedicationRoute domain) {

        if (domain == null) {
            return null;
        }

        MedicationRouteJpaEntity jpa = new MedicationRouteJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public MedicationRoute toDomain(MedicationRouteJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return MedicationRoute.restore(
                new MedicationRouteId(jpa.getId()),
                jpa.getCode(),
                jpa.getName(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
