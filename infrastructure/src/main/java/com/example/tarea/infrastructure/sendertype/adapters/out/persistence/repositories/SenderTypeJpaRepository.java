package com.example.tarea.infrastructure.sendertype.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SenderTypeJpaRepository extends JpaRepository<SenderTypeJpaEntity, UUID> {
}
