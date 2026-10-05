package com.example.tarea.infrastructure.emailcontact.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.emailcontact.usecase.DeleteEmailContactUseCase;
import com.example.tarea.application.emailcontact.usecase.GetEmailContactByIdUseCase;
import com.example.tarea.application.emailcontact.usecase.ListEmailContactUseCase;
import com.example.tarea.application.emailcontact.usecase.RegisterEmailContactUseCase;
import com.example.tarea.application.emailcontact.usecase.UpdateEmailContactUseCase;
import com.example.tarea.domain.emailcontact.port.repository.EmailContactRepository;
import com.example.tarea.infrastructure.emailcontact.adapters.out.persistence.mappers.EmailContactPersistenceMapper;
import com.example.tarea.infrastructure.emailcontact.adapters.out.persistence.repositories.EmailContactJpaRepository;
import com.example.tarea.infrastructure.emailcontact.adapters.out.persistence.repositories.EmailContactRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context emailcontact: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class EmailContactBeansConfig {

    @Bean
    public EmailContactPersistenceMapper emailContactPersistenceMapper() {
        return new EmailContactPersistenceMapper();
    }

    @Bean
    public EmailContactRepository emailContactRepository(EmailContactJpaRepository jpaRepository, EmailContactPersistenceMapper mapper) {
        return new EmailContactRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterEmailContactUseCase registerEmailContactUseCase(EmailContactRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterEmailContactUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateEmailContactUseCase updateEmailContactUseCase(EmailContactRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateEmailContactUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteEmailContactUseCase deleteEmailContactUseCase(EmailContactRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteEmailContactUseCase(repository, eventPublisher);
    }

    @Bean
    public GetEmailContactByIdUseCase getEmailContactByIdUseCase(EmailContactRepository repository) {
        return new GetEmailContactByIdUseCase(repository);
    }

    @Bean
    public ListEmailContactUseCase listEmailContactUseCase(EmailContactRepository repository) {
        return new ListEmailContactUseCase(repository);
    }
}
