package com.example.tarea.infrastructure.riskassessment.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.riskassessment.usecase.DeleteRiskAssessmentUseCase;
import com.example.tarea.application.riskassessment.usecase.GetRiskAssessmentByIdUseCase;
import com.example.tarea.application.riskassessment.usecase.ListRiskAssessmentUseCase;
import com.example.tarea.application.riskassessment.usecase.RegisterRiskAssessmentUseCase;
import com.example.tarea.application.riskassessment.usecase.UpdateRiskAssessmentUseCase;
import com.example.tarea.domain.riskassessment.port.repository.RiskAssessmentRepository;
import com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.mappers.RiskAssessmentPersistenceMapper;
import com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.repositories.RiskAssessmentJpaRepository;
import com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.repositories.RiskAssessmentRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context riskassessment: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class RiskAssessmentBeansConfig {

    @Bean
    public RiskAssessmentPersistenceMapper riskAssessmentPersistenceMapper() {
        return new RiskAssessmentPersistenceMapper();
    }

    @Bean
    public RiskAssessmentRepository riskAssessmentRepository(RiskAssessmentJpaRepository jpaRepository, RiskAssessmentPersistenceMapper mapper) {
        return new RiskAssessmentRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterRiskAssessmentUseCase registerRiskAssessmentUseCase(RiskAssessmentRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterRiskAssessmentUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateRiskAssessmentUseCase updateRiskAssessmentUseCase(RiskAssessmentRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateRiskAssessmentUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteRiskAssessmentUseCase deleteRiskAssessmentUseCase(RiskAssessmentRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteRiskAssessmentUseCase(repository, eventPublisher);
    }

    @Bean
    public GetRiskAssessmentByIdUseCase getRiskAssessmentByIdUseCase(RiskAssessmentRepository repository) {
        return new GetRiskAssessmentByIdUseCase(repository);
    }

    @Bean
    public ListRiskAssessmentUseCase listRiskAssessmentUseCase(RiskAssessmentRepository repository) {
        return new ListRiskAssessmentUseCase(repository);
    }
}
