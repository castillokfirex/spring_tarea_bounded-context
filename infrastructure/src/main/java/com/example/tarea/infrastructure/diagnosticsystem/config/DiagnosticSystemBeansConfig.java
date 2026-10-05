package com.example.tarea.infrastructure.diagnosticsystem.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.diagnosticsystem.usecase.DeleteDiagnosticSystemUseCase;
import com.example.tarea.application.diagnosticsystem.usecase.GetDiagnosticSystemByIdUseCase;
import com.example.tarea.application.diagnosticsystem.usecase.ListDiagnosticSystemUseCase;
import com.example.tarea.application.diagnosticsystem.usecase.RegisterDiagnosticSystemUseCase;
import com.example.tarea.application.diagnosticsystem.usecase.UpdateDiagnosticSystemUseCase;
import com.example.tarea.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;
import com.example.tarea.infrastructure.diagnosticsystem.adapters.out.persistence.mappers.DiagnosticSystemPersistenceMapper;
import com.example.tarea.infrastructure.diagnosticsystem.adapters.out.persistence.repositories.DiagnosticSystemJpaRepository;
import com.example.tarea.infrastructure.diagnosticsystem.adapters.out.persistence.repositories.DiagnosticSystemRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context diagnosticsystem: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class DiagnosticSystemBeansConfig {

    @Bean
    public DiagnosticSystemPersistenceMapper diagnosticSystemPersistenceMapper() {
        return new DiagnosticSystemPersistenceMapper();
    }

    @Bean
    public DiagnosticSystemRepository diagnosticSystemRepository(DiagnosticSystemJpaRepository jpaRepository, DiagnosticSystemPersistenceMapper mapper) {
        return new DiagnosticSystemRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterDiagnosticSystemUseCase registerDiagnosticSystemUseCase(DiagnosticSystemRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterDiagnosticSystemUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateDiagnosticSystemUseCase updateDiagnosticSystemUseCase(DiagnosticSystemRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateDiagnosticSystemUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteDiagnosticSystemUseCase deleteDiagnosticSystemUseCase(DiagnosticSystemRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteDiagnosticSystemUseCase(repository, eventPublisher);
    }

    @Bean
    public GetDiagnosticSystemByIdUseCase getDiagnosticSystemByIdUseCase(DiagnosticSystemRepository repository) {
        return new GetDiagnosticSystemByIdUseCase(repository);
    }

    @Bean
    public ListDiagnosticSystemUseCase listDiagnosticSystemUseCase(DiagnosticSystemRepository repository) {
        return new ListDiagnosticSystemUseCase(repository);
    }
}
