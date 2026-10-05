package com.example.tarea.application.medicationroute.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.medicationroute.command.RegisterMedicationRouteCommand;
import com.example.tarea.application.medicationroute.dto.MedicationRouteResponse;
import com.example.tarea.domain.medicationroute.model.aggregate.MedicationRoute;
import com.example.tarea.domain.medicationroute.port.repository.MedicationRouteRepository;

public class RegisterMedicationRouteUseCase {

    private final MedicationRouteRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterMedicationRouteUseCase(MedicationRouteRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public MedicationRouteResponse execute(RegisterMedicationRouteCommand command) {

        MedicationRoute medicationRoute = MedicationRoute.register(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCode(medicationRoute.code())) {
            throw new ConflictApplicationException(
                    "A MedicationRoute with the same code already exists");
        }
        if (repository.existsByName(medicationRoute.name())) {
            throw new ConflictApplicationException(
                    "A MedicationRoute with the same name already exists");
        }

        MedicationRoute saved = repository.save(medicationRoute);

        eventPublisher.publish(medicationRoute.domainEvents());
        medicationRoute.clearDomainEvents();

        return MedicationRouteResponse.fromDomain(saved);
    }
}
