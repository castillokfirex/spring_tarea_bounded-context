package com.example.tarea.infrastructure.conversationstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationStatusJpaRepository extends JpaRepository<ConversationStatusJpaEntity, UUID> {
}
