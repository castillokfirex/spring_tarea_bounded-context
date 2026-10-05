package com.example.tarea.domain.clinicalrecordstatus.model.aggregate;

import java.time.LocalDateTime;

import com.example.tarea.domain.clinicalrecordstatus.event.ClinicalRecordStatusDeletedEvent;
import com.example.tarea.domain.clinicalrecordstatus.event.ClinicalRecordStatusRegisteredEvent;
import com.example.tarea.domain.clinicalrecordstatus.event.ClinicalRecordStatusUpdatedEvent;
import com.example.tarea.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;

/**
 * Aggregate root del bounded context <b>clinicalrecordstatus</b> (tabla <code>clinical_record_statusses</code>).
 */
public class ClinicalRecordStatus extends AggregateRoot {

    private final ClinicalRecordStatusId id;
    private String code;
    private String name;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ClinicalRecordStatus(
            ClinicalRecordStatusId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ClinicalRecordStatus register(
            String code,
            String name,
            Boolean active) {

        LocalDateTime now = LocalDateTime.now();
        ClinicalRecordStatus aggregate = new ClinicalRecordStatus(
                ClinicalRecordStatusId.generate(),
                code,
                name,
                active,
                now,
                now);

        aggregate.recordEvent(new ClinicalRecordStatusRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ClinicalRecordStatus restore(
            ClinicalRecordStatusId id,
            String code,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ClinicalRecordStatus(
                id,
                code,
                name,
                active,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            Boolean active) {

        this.code = Guard.maxLength(Guard.notBlank(code, "code"), 20, "code");
        this.name = Guard.maxLength(Guard.notBlank(name, "name"), 50, "name");
        this.active = Guard.notNull(active, "active");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ClinicalRecordStatusUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ClinicalRecordStatusDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ClinicalRecordStatusId id() {
        return id;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }

    public Boolean active() {
        return active;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
