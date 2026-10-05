package com.example.tarea.application.medicationroute.usecase;

import com.example.tarea.application.medicationroute.dto.MedicationRouteResponse;
import com.example.tarea.domain.medicationroute.exception.MedicationRouteNotFoundException;
import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.example.tarea.domain.medicationroute.port.repository.MedicationRouteRepository;

public class GetMedicationRouteByIdUseCase {

    private final MedicationRouteRepository repository;

    public GetMedicationRouteByIdUseCase(MedicationRouteRepository repository) {
        this.repository = repository;
    }

    public MedicationRouteResponse execute(MedicationRouteId id) {
        return repository.findById(id)
                .map(MedicationRouteResponse::fromDomain)
                .orElseThrow(() -> new MedicationRouteNotFoundException(id));
    }
}
