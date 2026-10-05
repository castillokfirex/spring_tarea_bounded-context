package com.example.tarea.domain.stateregion.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.stateregion.event.StateRegionDeletedEvent;
import com.example.tarea.domain.stateregion.event.StateRegionRegisteredEvent;
import com.example.tarea.domain.stateregion.event.StateRegionUpdatedEvent;
import com.example.tarea.domain.stateregion.model.valueobject.StateRegionId;

/**
 * Aggregate root del bounded context <b>stateregion</b> (tabla <code>state_regions</code>).
 */
public class StateRegion extends AggregateRoot {

    private final StateRegionId id;
    private String nameRegion;
    private String codeRegion;
    private String description;
    private Boolean isActive;
    private UUID countryId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private StateRegion(
            StateRegionId id,
            String nameRegion,
            String codeRegion,
            String description,
            Boolean isActive,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.nameRegion = Guard.maxLength(Guard.notBlank(nameRegion, "nameRegion"), 50, "nameRegion");
        this.codeRegion = Guard.maxLength(codeRegion, 10, "codeRegion");
        this.description = Guard.maxLength(description, 100, "description");
        this.isActive = Guard.notNull(isActive, "isActive");
        this.countryId = Guard.notNull(countryId, "countryId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static StateRegion register(
            String nameRegion,
            String codeRegion,
            String description,
            Boolean isActive,
            UUID countryId) {

        LocalDateTime now = LocalDateTime.now();
        StateRegion aggregate = new StateRegion(
                StateRegionId.generate(),
                nameRegion,
                codeRegion,
                description,
                isActive,
                countryId,
                now,
                now);

        aggregate.recordEvent(new StateRegionRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static StateRegion restore(
            StateRegionId id,
            String nameRegion,
            String codeRegion,
            String description,
            Boolean isActive,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new StateRegion(
                id,
                nameRegion,
                codeRegion,
                description,
                isActive,
                countryId,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameRegion,
            String codeRegion,
            String description,
            Boolean isActive,
            UUID countryId) {

        this.nameRegion = Guard.maxLength(Guard.notBlank(nameRegion, "nameRegion"), 50, "nameRegion");
        this.codeRegion = Guard.maxLength(codeRegion, 10, "codeRegion");
        this.description = Guard.maxLength(description, 100, "description");
        this.isActive = Guard.notNull(isActive, "isActive");
        this.countryId = Guard.notNull(countryId, "countryId");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new StateRegionUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new StateRegionDeletedEvent(this.id, LocalDateTime.now()));
    }

    public StateRegionId id() {
        return id;
    }

    public String nameRegion() {
        return nameRegion;
    }

    public String codeRegion() {
        return codeRegion;
    }

    public String description() {
        return description;
    }

    public Boolean isActive() {
        return isActive;
    }

    public UUID countryId() {
        return countryId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
