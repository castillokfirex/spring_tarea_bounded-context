package com.example.tarea.infrastructure.professional.adapters.out.persistence.mappers;

import com.example.tarea.domain.professional.model.aggregate.Professional;
import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;
import com.example.tarea.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;

public class ProfessionalPersistenceMapper {

    public ProfessionalJpaEntity toJpa(Professional domain) {

        if (domain == null) {
            return null;
        }

        ProfessionalJpaEntity jpa = new ProfessionalJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setDocumentTypeId(domain.documentTypeId());
        jpa.setDocumentNumber(domain.documentNumber());
        jpa.setFirstName(domain.firstName());
        jpa.setLastName(domain.lastName());
        jpa.setProfessionalType(domain.professionalType());
        jpa.setLicenseNumber(domain.licenseNumber());
        jpa.setActive(domain.active());
        jpa.setCityId(domain.cityId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public Professional toDomain(ProfessionalJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return Professional.restore(
                new ProfessionalId(jpa.getId()),
                jpa.getDocumentTypeId(),
                jpa.getDocumentNumber(),
                jpa.getFirstName(),
                jpa.getLastName(),
                jpa.getProfessionalType(),
                jpa.getLicenseNumber(),
                jpa.getActive(),
                jpa.getCityId(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
