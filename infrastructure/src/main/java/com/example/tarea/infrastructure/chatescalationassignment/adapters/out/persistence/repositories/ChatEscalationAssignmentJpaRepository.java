package com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEscalationAssignmentJpaRepository extends JpaRepository<ChatEscalationAssignmentJpaEntity, UUID> {
}
