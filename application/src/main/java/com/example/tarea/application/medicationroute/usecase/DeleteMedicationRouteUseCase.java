package com.example.tarea.application.medicationroute.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.medicationroute.exception.MedicationRouteNotFoundException;
import com.example.tarea.domain.medicationroute.model.aggregate.MedicationRoute;
import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.example.tarea.domain.medicationroute.port.repository.MedicationRouteRepository;

public class DeleteMedicationRouteUseCase {

    private final MedicationRouteRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteMedicationRouteUseCase(MedicationRouteRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(MedicationRouteId id) {

        MedicationRoute medicationRoute = repository.findById(id)
                .orElseThrow(() -> new MedicationRouteNotFoundException(id));

        medicationRoute.delete();
        repository.delete(medicationRoute);

        eventPublisher.publish(medicationRoute.domainEvents());
        medicationRoute.clearDomainEvents();
    }
}
