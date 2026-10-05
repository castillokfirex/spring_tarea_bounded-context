package com.example.tarea.domain.clinicalrecord.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.clinicalrecord.event.ClinicalRecordDeletedEvent;
import com.example.tarea.domain.clinicalrecord.event.ClinicalRecordRegisteredEvent;
import com.example.tarea.domain.clinicalrecord.event.ClinicalRecordUpdatedEvent;
import com.example.tarea.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>clinicalrecord</b> (tabla <code>clinical_records</code>).
 */
public class ClinicalRecord extends AggregateRoot {

    private final ClinicalRecordId id;
    private UUID patientId;
    private LocalDateTime creationDate;
    private String recordNumber;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private UUID statusId;
    private final LocalDateTime createdAt;
    private final UUID createdBy;

    private ClinicalRecord(
            ClinicalRecordId id,
            UUID patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            UUID statusId,
            LocalDateTime createdAt,
            UUID createdBy) {
        this.id = Guard.notNull(id, "id");
        this.patientId = Guard.notNull(patientId, "patientId");
        this.creationDate = Guard.notNull(creationDate, "creationDate");
        this.recordNumber = Guard.maxLength(Guard.notBlank(recordNumber, "recordNumber"), 50, "recordNumber");
        this.openedAt = Guard.notNull(openedAt, "openedAt");
        this.closedAt = closedAt;
        this.statusId = Guard.notNull(statusId, "statusId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.createdBy = Guard.notNull(createdBy, "createdBy");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ClinicalRecord register(
            UUID patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            UUID statusId,
            UUID createdBy) {

        LocalDateTime now = LocalDateTime.now();
        ClinicalRecord aggregate = new ClinicalRecord(
                ClinicalRecordId.generate(),
                patientId,
                creationDate != null ? creationDate : now,
                recordNumber,
                openedAt != null ? openedAt : now,
                closedAt,
                statusId,
                now,
                createdBy);

        aggregate.recordEvent(new ClinicalRecordRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ClinicalRecord restore(
            ClinicalRecordId id,
            UUID patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            UUID statusId,
            LocalDateTime createdAt,
            UUID createdBy) {
        return new ClinicalRecord(
                id,
                patientId,
                creationDate,
                recordNumber,
                openedAt,
                closedAt,
                statusId,
                createdAt,
                createdBy);
    }

    public void update(
            UUID patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            UUID statusId) {

        this.patientId = Guard.notNull(patientId, "patientId");
        this.creationDate = creationDate != null ? creationDate : this.creationDate;
        this.recordNumber = Guard.maxLength(Guard.notBlank(recordNumber, "recordNumber"), 50, "recordNumber");
        this.openedAt = openedAt != null ? openedAt : this.openedAt;
        this.closedAt = closedAt;
        this.statusId = Guard.notNull(statusId, "statusId");

        recordEvent(new ClinicalRecordUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ClinicalRecordDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ClinicalRecordId id() {
        return id;
    }

    public UUID patientId() {
        return patientId;
    }

    public LocalDateTime creationDate() {
        return creationDate;
    }

    public String recordNumber() {
        return recordNumber;
    }

    public LocalDateTime openedAt() {
        return openedAt;
    }

    public LocalDateTime closedAt() {
        return closedAt;
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
}
