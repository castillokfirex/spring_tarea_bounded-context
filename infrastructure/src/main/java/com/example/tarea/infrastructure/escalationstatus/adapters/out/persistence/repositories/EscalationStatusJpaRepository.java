package com.example.tarea.infrastructure.escalationstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscalationStatusJpaRepository extends JpaRepository<EscalationStatusJpaEntity, UUID> {
}
