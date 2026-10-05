package com.example.tarea.infrastructure.phonecontact.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.phonecontact.usecase.DeletePhoneContactUseCase;
import com.example.tarea.application.phonecontact.usecase.GetPhoneContactByIdUseCase;
import com.example.tarea.application.phonecontact.usecase.ListPhoneContactUseCase;
import com.example.tarea.application.phonecontact.usecase.RegisterPhoneContactUseCase;
import com.example.tarea.application.phonecontact.usecase.UpdatePhoneContactUseCase;
import com.example.tarea.domain.phonecontact.port.repository.PhoneContactRepository;
import com.example.tarea.infrastructure.phonecontact.adapters.out.persistence.mappers.PhoneContactPersistenceMapper;
import com.example.tarea.infrastructure.phonecontact.adapters.out.persistence.repositories.PhoneContactJpaRepository;
import com.example.tarea.infrastructure.phonecontact.adapters.out.persistence.repositories.PhoneContactRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context phonecontact: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class PhoneContactBeansConfig {

    @Bean
    public PhoneContactPersistenceMapper phoneContactPersistenceMapper() {
        return new PhoneContactPersistenceMapper();
    }

    @Bean
    public PhoneContactRepository phoneContactRepository(PhoneContactJpaRepository jpaRepository, PhoneContactPersistenceMapper mapper) {
        return new PhoneContactRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterPhoneContactUseCase registerPhoneContactUseCase(PhoneContactRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterPhoneContactUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdatePhoneContactUseCase updatePhoneContactUseCase(PhoneContactRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdatePhoneContactUseCase(repository, eventPublisher);
    }

    @Bean
    public DeletePhoneContactUseCase deletePhoneContactUseCase(PhoneContactRepository repository, DomainEventPublisher eventPublisher) {
        return new DeletePhoneContactUseCase(repository, eventPublisher);
    }

    @Bean
    public GetPhoneContactByIdUseCase getPhoneContactByIdUseCase(PhoneContactRepository repository) {
        return new GetPhoneContactByIdUseCase(repository);
    }

    @Bean
    public ListPhoneContactUseCase listPhoneContactUseCase(PhoneContactRepository repository) {
        return new ListPhoneContactUseCase(repository);
    }
}
