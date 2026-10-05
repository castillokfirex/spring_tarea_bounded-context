package com.example.tarea.infrastructure.assessmenttype.config;

import com.example.tarea.application.assessmenttype.usecase.DeleteAssessmentTypeUseCase;
import com.example.tarea.application.assessmenttype.usecase.GetAssessmentTypeByIdUseCase;
import com.example.tarea.application.assessmenttype.usecase.ListAssessmentTypeUseCase;
import com.example.tarea.application.assessmenttype.usecase.RegisterAssessmentTypeUseCase;
import com.example.tarea.application.assessmenttype.usecase.UpdateAssessmentTypeUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.assessmenttype.port.repository.AssessmentTypeRepository;
import com.example.tarea.infrastructure.assessmenttype.adapters.out.persistence.mappers.AssessmentTypePersistenceMapper;
import com.example.tarea.infrastructure.assessmenttype.adapters.out.persistence.repositories.AssessmentTypeJpaRepository;
import com.example.tarea.infrastructure.assessmenttype.adapters.out.persistence.repositories.AssessmentTypeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context assessmenttype: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class AssessmentTypeBeansConfig {

    @Bean
    public AssessmentTypePersistenceMapper assessmentTypePersistenceMapper() {
        return new AssessmentTypePersistenceMapper();
    }

    @Bean
    public AssessmentTypeRepository assessmentTypeRepository(AssessmentTypeJpaRepository jpaRepository, AssessmentTypePersistenceMapper mapper) {
        return new AssessmentTypeRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterAssessmentTypeUseCase registerAssessmentTypeUseCase(AssessmentTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterAssessmentTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateAssessmentTypeUseCase updateAssessmentTypeUseCase(AssessmentTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateAssessmentTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteAssessmentTypeUseCase deleteAssessmentTypeUseCase(AssessmentTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteAssessmentTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public GetAssessmentTypeByIdUseCase getAssessmentTypeByIdUseCase(AssessmentTypeRepository repository) {
        return new GetAssessmentTypeByIdUseCase(repository);
    }

    @Bean
    public ListAssessmentTypeUseCase listAssessmentTypeUseCase(AssessmentTypeRepository repository) {
        return new ListAssessmentTypeUseCase(repository);
    }
}
