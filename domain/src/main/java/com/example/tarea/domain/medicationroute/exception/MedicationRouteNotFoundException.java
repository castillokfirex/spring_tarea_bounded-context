package com.example.tarea.domain.medicationroute.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;

public class MedicationRouteNotFoundException extends ResourceNotFoundException {

    public MedicationRouteNotFoundException(MedicationRouteId id) {
        super("MedicationRoute with id '" + id.value() + "' was not found");
    }
}
