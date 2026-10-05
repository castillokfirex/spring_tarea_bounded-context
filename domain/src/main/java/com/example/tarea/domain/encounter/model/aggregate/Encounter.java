package com.example.tarea.domain.encounter.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.encounter.event.EncounterDeletedEvent;
import com.example.tarea.domain.encounter.event.EncounterRegisteredEvent;
import com.example.tarea.domain.encounter.event.EncounterUpdatedEvent;
import com.example.tarea.domain.encounter.model.valueobject.EncounterId;

/**
 * Aggregate root del bounded context <b>encounter</b> (tabla <code>encounters</code>).
 */
public class Encounter extends AggregateRoot {

    private final EncounterId id;
    private UUID clinicalRecordId;
    private UUID professionalId;
    private UUID encounterTypeId;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String reasonForVisit;
    private String currentCondition;
    private UUID modalityId;
    private UUID statusId;
    private final LocalDateTime createdAt;
    private final UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;

    private Encounter(
            EncounterId id,
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy) {
        this.id = Guard.notNull(id, "id");
        this.clinicalRecordId = Guard.notNull(clinicalRecordId, "clinicalRecordId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.encounterTypeId = Guard.notNull(encounterTypeId, "encounterTypeId");
        this.startedAt = Guard.notNull(startedAt, "startedAt");
        this.endedAt = endedAt;
        this.reasonForVisit = Guard.notBlank(reasonForVisit, "reasonForVisit");
        this.currentCondition = Guard.notBlank(currentCondition, "currentCondition");
        this.modalityId = Guard.notNull(modalityId, "modalityId");
        this.statusId = Guard.notNull(statusId, "statusId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.createdBy = Guard.notNull(createdBy, "createdBy");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
        this.updatedBy = Guard.notNull(updatedBy, "updatedBy");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static Encounter register(
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            UUID createdBy,
            UUID updatedBy) {

        LocalDateTime now = LocalDateTime.now();
        Encounter aggregate = new Encounter(
                EncounterId.generate(),
                clinicalRecordId,
                professionalId,
                encounterTypeId,
                startedAt != null ? startedAt : now,
                endedAt,
                reasonForVisit,
                currentCondition,
                modalityId,
                statusId,
                now,
                createdBy,
                now,
                updatedBy);

        aggregate.recordEvent(new EncounterRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static Encounter restore(
            EncounterId id,
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy) {
        return new Encounter(
                id,
                clinicalRecordId,
                professionalId,
                encounterTypeId,
                startedAt,
                endedAt,
                reasonForVisit,
                currentCondition,
                modalityId,
                statusId,
                createdAt,
                createdBy,
                updatedAt,
                updatedBy);
    }

    public void update(
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            UUID updatedBy) {

        this.clinicalRecordId = Guard.notNull(clinicalRecordId, "clinicalRecordId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.encounterTypeId = Guard.notNull(encounterTypeId, "encounterTypeId");
        this.startedAt = startedAt != null ? startedAt : this.startedAt;
        this.endedAt = endedAt;
        this.reasonForVisit = Guard.notBlank(reasonForVisit, "reasonForVisit");
        this.currentCondition = Guard.notBlank(currentCondition, "currentCondition");
        this.modalityId = Guard.notNull(modalityId, "modalityId");
        this.statusId = Guard.notNull(statusId, "statusId");
        this.updatedBy = Guard.notNull(updatedBy, "updatedBy");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new EncounterUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new EncounterDeletedEvent(this.id, LocalDateTime.now()));
    }

    public EncounterId id() {
        return id;
    }

    public UUID clinicalRecordId() {
        return clinicalRecordId;
    }

    public UUID professionalId() {
        return professionalId;
    }

    public UUID encounterTypeId() {
        return encounterTypeId;
    }

    public LocalDateTime startedAt() {
        return startedAt;
    }

    public LocalDateTime endedAt() {
        return endedAt;
    }

    public String reasonForVisit() {
        return reasonForVisit;
    }

    public String currentCondition() {
        return currentCondition;
    }

    public UUID modalityId() {
        return modalityId;
    }

    public UUID statusId() {
        return statusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public UUID createdBy() {
        return createdBy;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public UUID updatedBy() {
        return updatedBy;
    }
}
