package com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.mappers;

import com.example.tarea.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.example.tarea.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.entity.ProviderModelAiJpaEntity;

public class ProviderModelAiPersistenceMapper {

    public ProviderModelAiJpaEntity toJpa(ProviderModelAi domain) {

        if (domain == null) {
            return null;
        }

        ProviderModelAiJpaEntity jpa = new ProviderModelAiJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameProviderAi(domain.nameProviderAi());
        jpa.setRazonSocial(domain.razonSocial());
        jpa.setSitioWeb(domain.sitioWeb());
        jpa.setIsActive(domain.isActive());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ProviderModelAi toDomain(ProviderModelAiJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ProviderModelAi.restore(
                new ProviderModelAiId(jpa.getId()),
                jpa.getNameProviderAi(),
                jpa.getRazonSocial(),
                jpa.getSitioWeb(),
                jpa.getIsActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
