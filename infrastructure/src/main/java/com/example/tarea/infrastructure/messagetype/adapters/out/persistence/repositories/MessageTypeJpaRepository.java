package com.example.tarea.infrastructure.messagetype.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTypeJpaRepository extends JpaRepository<MessageTypeJpaEntity, UUID> {
}
