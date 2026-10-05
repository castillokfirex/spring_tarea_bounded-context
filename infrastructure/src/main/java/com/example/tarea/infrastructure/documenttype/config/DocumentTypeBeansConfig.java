package com.example.tarea.infrastructure.documenttype.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.documenttype.usecase.DeleteDocumentTypeUseCase;
import com.example.tarea.application.documenttype.usecase.GetDocumentTypeByIdUseCase;
import com.example.tarea.application.documenttype.usecase.ListDocumentTypeUseCase;
import com.example.tarea.application.documenttype.usecase.RegisterDocumentTypeUseCase;
import com.example.tarea.application.documenttype.usecase.UpdateDocumentTypeUseCase;
import com.example.tarea.domain.documenttype.port.repository.DocumentTypeRepository;
import com.example.tarea.infrastructure.documenttype.adapters.out.persistence.mappers.DocumentTypePersistenceMapper;
import com.example.tarea.infrastructure.documenttype.adapters.out.persistence.repositories.DocumentTypeJpaRepository;
import com.example.tarea.infrastructure.documenttype.adapters.out.persistence.repositories.DocumentTypeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context documenttype: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class DocumentTypeBeansConfig {

    @Bean
    public DocumentTypePersistenceMapper documentTypePersistenceMapper() {
        return new DocumentTypePersistenceMapper();
    }

    @Bean
    public DocumentTypeRepository documentTypeRepository(DocumentTypeJpaRepository jpaRepository, DocumentTypePersistenceMapper mapper) {
        return new DocumentTypeRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterDocumentTypeUseCase registerDocumentTypeUseCase(DocumentTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterDocumentTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateDocumentTypeUseCase updateDocumentTypeUseCase(DocumentTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateDocumentTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteDocumentTypeUseCase deleteDocumentTypeUseCase(DocumentTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteDocumentTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public GetDocumentTypeByIdUseCase getDocumentTypeByIdUseCase(DocumentTypeRepository repository) {
        return new GetDocumentTypeByIdUseCase(repository);
    }

    @Bean
    public ListDocumentTypeUseCase listDocumentTypeUseCase(DocumentTypeRepository repository) {
        return new ListDocumentTypeUseCase(repository);
    }
}
