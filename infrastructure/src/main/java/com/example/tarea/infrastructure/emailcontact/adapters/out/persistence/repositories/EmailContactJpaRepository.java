package com.example.tarea.infrastructure.emailcontact.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailContactJpaRepository extends JpaRepository<EmailContactJpaEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, UUID id);
}
