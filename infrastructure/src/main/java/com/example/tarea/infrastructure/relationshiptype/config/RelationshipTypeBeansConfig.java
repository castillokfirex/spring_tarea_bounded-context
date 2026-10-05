package com.example.tarea.infrastructure.relationshiptype.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.relationshiptype.usecase.DeleteRelationshipTypeUseCase;
import com.example.tarea.application.relationshiptype.usecase.GetRelationshipTypeByIdUseCase;
import com.example.tarea.application.relationshiptype.usecase.ListRelationshipTypeUseCase;
import com.example.tarea.application.relationshiptype.usecase.RegisterRelationshipTypeUseCase;
import com.example.tarea.application.relationshiptype.usecase.UpdateRelationshipTypeUseCase;
import com.example.tarea.domain.relationshiptype.port.repository.RelationshipTypeRepository;
import com.example.tarea.infrastructure.relationshiptype.adapters.out.persistence.mappers.RelationshipTypePersistenceMapper;
import com.example.tarea.infrastructure.relationshiptype.adapters.out.persistence.repositories.RelationshipTypeJpaRepository;
import com.example.tarea.infrastructure.relationshiptype.adapters.out.persistence.repositories.RelationshipTypeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context relationshiptype: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class RelationshipTypeBeansConfig {

    @Bean
    public RelationshipTypePersistenceMapper relationshipTypePersistenceMapper() {
        return new RelationshipTypePersistenceMapper();
    }

    @Bean
    public RelationshipTypeRepository relationshipTypeRepository(RelationshipTypeJpaRepository jpaRepository, RelationshipTypePersistenceMapper mapper) {
        return new RelationshipTypeRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterRelationshipTypeUseCase registerRelationshipTypeUseCase(RelationshipTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterRelationshipTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateRelationshipTypeUseCase updateRelationshipTypeUseCase(RelationshipTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateRelationshipTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteRelationshipTypeUseCase deleteRelationshipTypeUseCase(RelationshipTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteRelationshipTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public GetRelationshipTypeByIdUseCase getRelationshipTypeByIdUseCase(RelationshipTypeRepository repository) {
        return new GetRelationshipTypeByIdUseCase(repository);
    }

    @Bean
    public ListRelationshipTypeUseCase listRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        return new ListRelationshipTypeUseCase(repository);
    }
}
