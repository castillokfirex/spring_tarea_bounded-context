package com.example.tarea.domain.country.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.country.event.CountryDeletedEvent;
import com.example.tarea.domain.country.event.CountryRegisteredEvent;
import com.example.tarea.domain.country.event.CountryUpdatedEvent;
import com.example.tarea.domain.country.model.valueobject.CountryId;

/**
 * Aggregate root del bounded context <b>country</b> (tabla <code>countries</code>).
 */
public class Country extends AggregateRoot {

    private final CountryId id;
    private String nameCountry;
    private String codeCountry;
    private String description;
    private Boolean isActive;
    private String telephonePrefix;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Country(
            CountryId id,
            String nameCountry,
            String codeCountry,
            String description,
            Boolean isActive,
            String telephonePrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.nameCountry = Guard.maxLength(Guard.notBlank(nameCountry, "nameCountry"), 50, "nameCountry");
        this.codeCountry = Guard.maxLength(codeCountry, 10, "codeCountry");
        this.description = Guard.maxLength(description, 100, "description");
        this.isActive = Guard.notNull(isActive, "isActive");
        this.telephonePrefix = Guard.maxLength(telephonePrefix, 5, "telephonePrefix");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static Country register(
            String nameCountry,
            String codeCountry,
            String description,
            Boolean isActive,
            String telephonePrefix) {

        LocalDateTime now = LocalDateTime.now();
        Country aggregate = new Country(
                CountryId.generate(),
                nameCountry,
                codeCountry,
                description,
                isActive,
                telephonePrefix,
                now,
                now);

        aggregate.recordEvent(new CountryRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static Country restore(
            CountryId id,
            String nameCountry,
            String codeCountry,
            String description,
            Boolean isActive,
            String telephonePrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Country(
                id,
                nameCountry,
                codeCountry,
                description,
                isActive,
                telephonePrefix,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameCountry,
            String codeCountry,
            String description,
            Boolean isActive,
            String telephonePrefix) {

        this.nameCountry = Guard.maxLength(Guard.notBlank(nameCountry, "nameCountry"), 50, "nameCountry");
        this.codeCountry = Guard.maxLength(codeCountry, 10, "codeCountry");
        this.description = Guard.maxLength(description, 100, "description");
        this.isActive = Guard.notNull(isActive, "isActive");
        this.telephonePrefix = Guard.maxLength(telephonePrefix, 5, "telephonePrefix");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new CountryUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new CountryDeletedEvent(this.id, LocalDateTime.now()));
    }

    public CountryId id() {
        return id;
    }

    public String nameCountry() {
        return nameCountry;
    }

    public String codeCountry() {
        return codeCountry;
    }

    public String description() {
        return description;
    }

    public Boolean isActive() {
        return isActive;
    }

    public String telephonePrefix() {
        return telephonePrefix;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
