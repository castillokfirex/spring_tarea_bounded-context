package com.example.tarea.infrastructure.mentalstatusexam.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentalStatusExamJpaRepository extends JpaRepository<MentalStatusExamJpaEntity, UUID> {
}
