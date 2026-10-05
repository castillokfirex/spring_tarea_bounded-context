package com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatParticipantJpaRepository extends JpaRepository<ChatParticipantJpaEntity, UUID> {
}
