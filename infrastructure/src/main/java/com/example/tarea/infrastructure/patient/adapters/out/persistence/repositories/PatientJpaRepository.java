package com.example.tarea.infrastructure.patient.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientJpaRepository extends JpaRepository<PatientJpaEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, UUID id);

    boolean existsByDocumentTypeIdAndDocumentNumber(UUID documentTypeId, String documentNumber);

    boolean existsByDocumentTypeIdAndDocumentNumberAndIdNot(UUID documentTypeId, String documentNumber, UUID id);
}
