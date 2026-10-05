package com.example.tarea.infrastructure.messagetype.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.messagetype.usecase.DeleteMessageTypeUseCase;
import com.example.tarea.application.messagetype.usecase.GetMessageTypeByIdUseCase;
import com.example.tarea.application.messagetype.usecase.ListMessageTypeUseCase;
import com.example.tarea.application.messagetype.usecase.RegisterMessageTypeUseCase;
import com.example.tarea.application.messagetype.usecase.UpdateMessageTypeUseCase;
import com.example.tarea.domain.messagetype.port.repository.MessageTypeRepository;
import com.example.tarea.infrastructure.messagetype.adapters.out.persistence.mappers.MessageTypePersistenceMapper;
import com.example.tarea.infrastructure.messagetype.adapters.out.persistence.repositories.MessageTypeJpaRepository;
import com.example.tarea.infrastructure.messagetype.adapters.out.persistence.repositories.MessageTypeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context messagetype: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class MessageTypeBeansConfig {

    @Bean
    public MessageTypePersistenceMapper messageTypePersistenceMapper() {
        return new MessageTypePersistenceMapper();
    }

    @Bean
    public MessageTypeRepository messageTypeRepository(MessageTypeJpaRepository jpaRepository, MessageTypePersistenceMapper mapper) {
        return new MessageTypeRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterMessageTypeUseCase registerMessageTypeUseCase(MessageTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterMessageTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateMessageTypeUseCase updateMessageTypeUseCase(MessageTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateMessageTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteMessageTypeUseCase deleteMessageTypeUseCase(MessageTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteMessageTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public GetMessageTypeByIdUseCase getMessageTypeByIdUseCase(MessageTypeRepository repository) {
        return new GetMessageTypeByIdUseCase(repository);
    }

    @Bean
    public ListMessageTypeUseCase listMessageTypeUseCase(MessageTypeRepository repository) {
        return new ListMessageTypeUseCase(repository);
    }
}
