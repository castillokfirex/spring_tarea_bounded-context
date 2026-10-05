package com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.entity.ProviderModelAiJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderModelAiJpaRepository extends JpaRepository<ProviderModelAiJpaEntity, UUID> {
}
