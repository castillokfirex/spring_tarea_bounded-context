package com.example.tarea.application.country.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.country.model.aggregate.Country;

public record CountryResponse(
        UUID id,
        String nameCountry,
        String codeCountry,
        String description,
        Boolean isActive,
        String telephonePrefix,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static CountryResponse fromDomain(Country aggregate) {
        return new CountryResponse(
                aggregate.id().value(),
                aggregate.nameCountry(),
                aggregate.codeCountry(),
                aggregate.description(),
                aggregate.isActive(),
                aggregate.telephonePrefix(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
