package com.example.tarea.infrastructure.study.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.study.usecase.DeleteStudyUseCase;
import com.example.tarea.application.study.usecase.GetStudyByIdUseCase;
import com.example.tarea.application.study.usecase.ListStudyUseCase;
import com.example.tarea.application.study.usecase.RegisterStudyUseCase;
import com.example.tarea.application.study.usecase.UpdateStudyUseCase;
import com.example.tarea.domain.study.port.repository.StudyRepository;
import com.example.tarea.infrastructure.study.adapters.out.persistence.mappers.StudyPersistenceMapper;
import com.example.tarea.infrastructure.study.adapters.out.persistence.repositories.StudyJpaRepository;
import com.example.tarea.infrastructure.study.adapters.out.persistence.repositories.StudyRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context study: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class StudyBeansConfig {

    @Bean
    public StudyPersistenceMapper studyPersistenceMapper() {
        return new StudyPersistenceMapper();
    }

    @Bean
    public StudyRepository studyRepository(StudyJpaRepository jpaRepository, StudyPersistenceMapper mapper) {
        return new StudyRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterStudyUseCase registerStudyUseCase(StudyRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterStudyUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateStudyUseCase updateStudyUseCase(StudyRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateStudyUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteStudyUseCase deleteStudyUseCase(StudyRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteStudyUseCase(repository, eventPublisher);
    }

    @Bean
    public GetStudyByIdUseCase getStudyByIdUseCase(StudyRepository repository) {
        return new GetStudyByIdUseCase(repository);
    }

    @Bean
    public ListStudyUseCase listStudyUseCase(StudyRepository repository) {
        return new ListStudyUseCase(repository);
    }
}
