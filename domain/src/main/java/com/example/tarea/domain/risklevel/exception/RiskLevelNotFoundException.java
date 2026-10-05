package com.example.tarea.domain.risklevel.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.risklevel.model.valueobject.RiskLevelId;

public class RiskLevelNotFoundException extends ResourceNotFoundException {

    public RiskLevelNotFoundException(RiskLevelId id) {
        super("RiskLevel with id '" + id.value() + "' was not found");
    }
}
