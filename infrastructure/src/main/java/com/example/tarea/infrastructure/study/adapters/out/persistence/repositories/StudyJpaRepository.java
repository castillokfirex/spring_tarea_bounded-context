package com.example.tarea.infrastructure.study.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyJpaRepository extends JpaRepository<StudyJpaEntity, UUID> {
}
