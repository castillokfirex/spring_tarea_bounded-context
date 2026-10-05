package com.example.tarea.infrastructure.professional.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalJpaRepository extends JpaRepository<ProfessionalJpaEntity, UUID> {

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumberAndIdNot(String documentNumber, UUID id);

    boolean existsByFirstName(String firstName);

    boolean existsByFirstNameAndIdNot(String firstName, UUID id);

    boolean existsByLastName(String lastName);

    boolean existsByLastNameAndIdNot(String lastName, UUID id);

    boolean existsByLicenseNumber(String licenseNumber);

    boolean existsByLicenseNumberAndIdNot(String licenseNumber, UUID id);
}
