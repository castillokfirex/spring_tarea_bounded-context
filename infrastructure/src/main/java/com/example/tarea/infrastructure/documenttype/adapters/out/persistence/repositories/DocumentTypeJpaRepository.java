package com.example.tarea.infrastructure.documenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeJpaRepository extends JpaRepository<DocumentTypeJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}
