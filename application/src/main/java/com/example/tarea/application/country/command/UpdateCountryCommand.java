package com.example.tarea.application.country.command;

import com.example.tarea.domain.country.model.valueobject.CountryId;

public record UpdateCountryCommand(
        CountryId id,
        String nameCountry,
        String codeCountry,
        String description,
        Boolean isActive,
        String telephonePrefix
) {
}
