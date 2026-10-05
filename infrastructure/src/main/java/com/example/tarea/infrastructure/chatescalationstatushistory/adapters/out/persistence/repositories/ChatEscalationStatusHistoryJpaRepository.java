package com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEscalationStatusHistoryJpaRepository extends JpaRepository<ChatEscalationStatusHistoryJpaEntity, UUID> {
}
