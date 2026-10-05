package com.example.tarea.domain.citymunicipality.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.citymunicipality.event.CityMunicipalityDeletedEvent;
import com.example.tarea.domain.citymunicipality.event.CityMunicipalityRegisteredEvent;
import com.example.tarea.domain.citymunicipality.event.CityMunicipalityUpdatedEvent;
import com.example.tarea.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>citymunicipality</b> (tabla <code>city_municipalities</code>).
 */
public class CityMunicipality extends AggregateRoot {

    private final CityMunicipalityId id;
    private String nameCity;
    private String codeCity;
    private String description;
    private Boolean isActive;
    private UUID regionId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CityMunicipality(
            CityMunicipalityId id,
            String nameCity,
            String codeCity,
            String description,
            Boolean isActive,
            UUID regionId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.nameCity = Guard.maxLength(Guard.notBlank(nameCity, "nameCity"), 50, "nameCity");
        this.codeCity = Guard.maxLength(codeCity, 10, "codeCity");
        this.description = Guard.maxLength(description, 100, "description");
        this.isActive = Guard.notNull(isActive, "isActive");
        this.regionId = Guard.notNull(regionId, "regionId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static CityMunicipality register(
            String nameCity,
            String codeCity,
            String description,
            Boolean isActive,
            UUID regionId) {

        LocalDateTime now = LocalDateTime.now();
        CityMunicipality aggregate = new CityMunicipality(
                CityMunicipalityId.generate(),
                nameCity,
                codeCity,
                description,
                isActive,
                regionId,
                now,
                now);

        aggregate.recordEvent(new CityMunicipalityRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static CityMunicipality restore(
            CityMunicipalityId id,
            String nameCity,
            String codeCity,
            String description,
            Boolean isActive,
            UUID regionId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new CityMunicipality(
                id,
                nameCity,
                codeCity,
                description,
                isActive,
                regionId,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameCity,
            String codeCity,
            String description,
            Boolean isActive,
            UUID regionId) {

        this.nameCity = Guard.maxLength(Guard.notBlank(nameCity, "nameCity"), 50, "nameCity");
        this.codeCity = Guard.maxLength(codeCity, 10, "codeCity");
        this.description = Guard.maxLength(description, 100, "description");
        this.isActive = Guard.notNull(isActive, "isActive");
        this.regionId = Guard.notNull(regionId, "regionId");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new CityMunicipalityUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new CityMunicipalityDeletedEvent(this.id, LocalDateTime.now()));
    }

    public CityMunicipalityId id() {
        return id;
    }

    public String nameCity() {
        return nameCity;
    }

    public String codeCity() {
        return codeCity;
    }

    public String description() {
        return description;
    }

    public Boolean isActive() {
        return isActive;
    }

    public UUID regionId() {
        return regionId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
