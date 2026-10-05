package com.example.tarea.infrastructure.professionalstudy.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalStudyJpaRepository extends JpaRepository<ProfessionalStudyJpaEntity, UUID> {
}
