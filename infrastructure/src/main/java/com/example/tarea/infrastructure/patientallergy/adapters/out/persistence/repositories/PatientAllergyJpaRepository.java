package com.example.tarea.infrastructure.patientallergy.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAllergyJpaRepository extends JpaRepository<PatientAllergyJpaEntity, UUID> {
}
