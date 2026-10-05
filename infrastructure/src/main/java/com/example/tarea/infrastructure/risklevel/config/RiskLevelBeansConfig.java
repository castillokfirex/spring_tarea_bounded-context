package com.example.tarea.infrastructure.risklevel.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.risklevel.usecase.DeleteRiskLevelUseCase;
import com.example.tarea.application.risklevel.usecase.GetRiskLevelByIdUseCase;
import com.example.tarea.application.risklevel.usecase.ListRiskLevelUseCase;
import com.example.tarea.application.risklevel.usecase.RegisterRiskLevelUseCase;
import com.example.tarea.application.risklevel.usecase.UpdateRiskLevelUseCase;
import com.example.tarea.domain.risklevel.port.repository.RiskLevelRepository;
import com.example.tarea.infrastructure.risklevel.adapters.out.persistence.mappers.RiskLevelPersistenceMapper;
import com.example.tarea.infrastructure.risklevel.adapters.out.persistence.repositories.RiskLevelJpaRepository;
import com.example.tarea.infrastructure.risklevel.adapters.out.persistence.repositories.RiskLevelRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context risklevel: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class RiskLevelBeansConfig {

    @Bean
    public RiskLevelPersistenceMapper riskLevelPersistenceMapper() {
        return new RiskLevelPersistenceMapper();
    }

    @Bean
    public RiskLevelRepository riskLevelRepository(RiskLevelJpaRepository jpaRepository, RiskLevelPersistenceMapper mapper) {
        return new RiskLevelRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterRiskLevelUseCase registerRiskLevelUseCase(RiskLevelRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterRiskLevelUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateRiskLevelUseCase updateRiskLevelUseCase(RiskLevelRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateRiskLevelUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteRiskLevelUseCase deleteRiskLevelUseCase(RiskLevelRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteRiskLevelUseCase(repository, eventPublisher);
    }

    @Bean
    public GetRiskLevelByIdUseCase getRiskLevelByIdUseCase(RiskLevelRepository repository) {
        return new GetRiskLevelByIdUseCase(repository);
    }

    @Bean
    public ListRiskLevelUseCase listRiskLevelUseCase(RiskLevelRepository repository) {
        return new ListRiskLevelUseCase(repository);
    }
}
