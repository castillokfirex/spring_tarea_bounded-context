package com.example.tarea.infrastructure.medicationroute.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.medicationroute.usecase.DeleteMedicationRouteUseCase;
import com.example.tarea.application.medicationroute.usecase.GetMedicationRouteByIdUseCase;
import com.example.tarea.application.medicationroute.usecase.ListMedicationRouteUseCase;
import com.example.tarea.application.medicationroute.usecase.RegisterMedicationRouteUseCase;
import com.example.tarea.application.medicationroute.usecase.UpdateMedicationRouteUseCase;
import com.example.tarea.domain.medicationroute.port.repository.MedicationRouteRepository;
import com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.mappers.MedicationRoutePersistenceMapper;
import com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.repositories.MedicationRouteJpaRepository;
import com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.repositories.MedicationRouteRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context medicationroute: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class MedicationRouteBeansConfig {

    @Bean
    public MedicationRoutePersistenceMapper medicationRoutePersistenceMapper() {
        return new MedicationRoutePersistenceMapper();
    }

    @Bean
    public MedicationRouteRepository medicationRouteRepository(MedicationRouteJpaRepository jpaRepository, MedicationRoutePersistenceMapper mapper) {
        return new MedicationRouteRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterMedicationRouteUseCase registerMedicationRouteUseCase(MedicationRouteRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterMedicationRouteUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateMedicationRouteUseCase updateMedicationRouteUseCase(MedicationRouteRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateMedicationRouteUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteMedicationRouteUseCase deleteMedicationRouteUseCase(MedicationRouteRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteMedicationRouteUseCase(repository, eventPublisher);
    }

    @Bean
    public GetMedicationRouteByIdUseCase getMedicationRouteByIdUseCase(MedicationRouteRepository repository) {
        return new GetMedicationRouteByIdUseCase(repository);
    }

    @Bean
    public ListMedicationRouteUseCase listMedicationRouteUseCase(MedicationRouteRepository repository) {
        return new ListMedicationRouteUseCase(repository);
    }
}
