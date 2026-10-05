package com.example.tarea.infrastructure.clinicalnote.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalNoteJpaRepository extends JpaRepository<ClinicalNoteJpaEntity, UUID> {
}
