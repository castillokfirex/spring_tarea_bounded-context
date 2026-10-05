package com.example.tarea.application.medicationroute.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.medicationroute.command.UpdateMedicationRouteCommand;
import com.example.tarea.application.medicationroute.dto.MedicationRouteResponse;
import com.example.tarea.domain.medicationroute.exception.MedicationRouteNotFoundException;
import com.example.tarea.domain.medicationroute.model.aggregate.MedicationRoute;
import com.example.tarea.domain.medicationroute.port.repository.MedicationRouteRepository;

public class UpdateMedicationRouteUseCase {

    private final MedicationRouteRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateMedicationRouteUseCase(MedicationRouteRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public MedicationRouteResponse execute(UpdateMedicationRouteCommand command) {

        MedicationRoute medicationRoute = repository.findById(command.id())
                .orElseThrow(() -> new MedicationRouteNotFoundException(command.id()));

        medicationRoute.update(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCodeAndIdNot(medicationRoute.code(), medicationRoute.id())) {
            throw new ConflictApplicationException(
                    "A MedicationRoute with the same code already exists");
        }
        if (repository.existsByNameAndIdNot(medicationRoute.name(), medicationRoute.id())) {
            throw new ConflictApplicationException(
                    "A MedicationRoute with the same name already exists");
        }

        MedicationRoute saved = repository.save(medicationRoute);

        eventPublisher.publish(medicationRoute.domainEvents());
        medicationRoute.clearDomainEvents();

        return MedicationRouteResponse.fromDomain(saved);
    }
}
