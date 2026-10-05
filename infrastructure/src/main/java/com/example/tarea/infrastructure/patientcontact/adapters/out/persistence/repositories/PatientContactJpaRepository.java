package com.example.tarea.infrastructure.patientcontact.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientContactJpaRepository extends JpaRepository<PatientContactJpaEntity, UUID> {
}
