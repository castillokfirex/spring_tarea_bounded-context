package com.example.tarea.infrastructure.clinicalrecord.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalRecordJpaRepository extends JpaRepository<ClinicalRecordJpaEntity, UUID> {

    boolean existsByRecordNumber(String recordNumber);

    boolean existsByRecordNumberAndIdNot(String recordNumber, UUID id);
}
