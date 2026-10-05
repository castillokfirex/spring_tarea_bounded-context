package com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEscalationJpaRepository extends JpaRepository<ChatEscalationJpaEntity, UUID> {
}
