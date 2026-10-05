package com.example.tarea.infrastructure.sendertype.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.sendertype.usecase.DeleteSenderTypeUseCase;
import com.example.tarea.application.sendertype.usecase.GetSenderTypeByIdUseCase;
import com.example.tarea.application.sendertype.usecase.ListSenderTypeUseCase;
import com.example.tarea.application.sendertype.usecase.RegisterSenderTypeUseCase;
import com.example.tarea.application.sendertype.usecase.UpdateSenderTypeUseCase;
import com.example.tarea.domain.sendertype.port.repository.SenderTypeRepository;
import com.example.tarea.infrastructure.sendertype.adapters.out.persistence.mappers.SenderTypePersistenceMapper;
import com.example.tarea.infrastructure.sendertype.adapters.out.persistence.repositories.SenderTypeJpaRepository;
import com.example.tarea.infrastructure.sendertype.adapters.out.persistence.repositories.SenderTypeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context sendertype: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class SenderTypeBeansConfig {

    @Bean
    public SenderTypePersistenceMapper senderTypePersistenceMapper() {
        return new SenderTypePersistenceMapper();
    }

    @Bean
    public SenderTypeRepository senderTypeRepository(SenderTypeJpaRepository jpaRepository, SenderTypePersistenceMapper mapper) {
        return new SenderTypeRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterSenderTypeUseCase registerSenderTypeUseCase(SenderTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterSenderTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateSenderTypeUseCase updateSenderTypeUseCase(SenderTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateSenderTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteSenderTypeUseCase deleteSenderTypeUseCase(SenderTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteSenderTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public GetSenderTypeByIdUseCase getSenderTypeByIdUseCase(SenderTypeRepository repository) {
        return new GetSenderTypeByIdUseCase(repository);
    }

    @Bean
    public ListSenderTypeUseCase listSenderTypeUseCase(SenderTypeRepository repository) {
        return new ListSenderTypeUseCase(repository);
    }
}
