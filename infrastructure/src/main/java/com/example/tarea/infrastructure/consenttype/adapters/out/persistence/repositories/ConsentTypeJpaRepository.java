package com.example.tarea.infrastructure.consenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsentTypeJpaRepository extends JpaRepository<ConsentTypeJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}
