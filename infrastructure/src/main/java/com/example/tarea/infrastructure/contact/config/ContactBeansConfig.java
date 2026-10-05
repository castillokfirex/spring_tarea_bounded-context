package com.example.tarea.infrastructure.contact.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.contact.usecase.DeleteContactUseCase;
import com.example.tarea.application.contact.usecase.GetContactByIdUseCase;
import com.example.tarea.application.contact.usecase.ListContactUseCase;
import com.example.tarea.application.contact.usecase.RegisterContactUseCase;
import com.example.tarea.application.contact.usecase.UpdateContactUseCase;
import com.example.tarea.domain.contact.port.repository.ContactRepository;
import com.example.tarea.infrastructure.contact.adapters.out.persistence.mappers.ContactPersistenceMapper;
import com.example.tarea.infrastructure.contact.adapters.out.persistence.repositories.ContactJpaRepository;
import com.example.tarea.infrastructure.contact.adapters.out.persistence.repositories.ContactRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context contact: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ContactBeansConfig {

    @Bean
    public ContactPersistenceMapper contactPersistenceMapper() {
        return new ContactPersistenceMapper();
    }

    @Bean
    public ContactRepository contactRepository(ContactJpaRepository jpaRepository, ContactPersistenceMapper mapper) {
        return new ContactRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterContactUseCase registerContactUseCase(ContactRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterContactUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateContactUseCase updateContactUseCase(ContactRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateContactUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteContactUseCase deleteContactUseCase(ContactRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteContactUseCase(repository, eventPublisher);
    }

    @Bean
    public GetContactByIdUseCase getContactByIdUseCase(ContactRepository repository) {
        return new GetContactByIdUseCase(repository);
    }

    @Bean
    public ListContactUseCase listContactUseCase(ContactRepository repository) {
        return new ListContactUseCase(repository);
    }
}
