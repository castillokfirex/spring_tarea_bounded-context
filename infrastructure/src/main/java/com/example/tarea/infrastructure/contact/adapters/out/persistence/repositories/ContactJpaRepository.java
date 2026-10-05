package com.example.tarea.infrastructure.contact.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactJpaRepository extends JpaRepository<ContactJpaEntity, UUID> {
}
