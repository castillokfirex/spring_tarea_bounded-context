package com.example.tarea.domain.professional.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.professional.event.ProfessionalDeletedEvent;
import com.example.tarea.domain.professional.event.ProfessionalRegisteredEvent;
import com.example.tarea.domain.professional.event.ProfessionalUpdatedEvent;
import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;

/**
 * Aggregate root del bounded context <b>professional</b> (tabla <code>professionals</code>).
 */
public class Professional extends AggregateRoot {

    private final ProfessionalId id;
    private UUID documentTypeId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private UUID professionalType;
    private String licenseNumber;
    private Boolean active;
    private UUID cityId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Professional(
            ProfessionalId id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalType,
            String licenseNumber,
            Boolean active,
            UUID cityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.documentTypeId = Guard.notNull(documentTypeId, "documentTypeId");
        this.documentNumber = Guard.maxLength(Guard.notBlank(documentNumber, "documentNumber"), 30, "documentNumber");
        this.firstName = Guard.maxLength(Guard.notBlank(firstName, "firstName"), 60, "firstName");
        this.lastName = Guard.maxLength(Guard.notBlank(lastName, "lastName"), 60, "lastName");
        this.professionalType = Guard.notNull(professionalType, "professionalType");
        this.licenseNumber = Guard.maxLength(Guard.notBlank(licenseNumber, "licenseNumber"), 100, "licenseNumber");
        this.active = Guard.notNull(active, "active");
        this.cityId = Guard.notNull(cityId, "cityId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static Professional register(
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalType,
            String licenseNumber,
            Boolean active,
            UUID cityId) {

        LocalDateTime now = LocalDateTime.now();
        Professional aggregate = new Professional(
                ProfessionalId.generate(),
                documentTypeId,
                documentNumber,
                firstName,
                lastName,
                professionalType,
                licenseNumber,
                active,
                cityId,
                now,
                now);

        aggregate.recordEvent(new ProfessionalRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static Professional restore(
            ProfessionalId id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalType,
            String licenseNumber,
            Boolean active,
            UUID cityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Professional(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                lastName,
                professionalType,
                licenseNumber,
                active,
                cityId,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalType,
            String licenseNumber,
            Boolean active,
            UUID cityId) {

        this.documentTypeId = Guard.notNull(documentTypeId, "documentTypeId");
        this.documentNumber = Guard.maxLength(Guard.notBlank(documentNumber, "documentNumber"), 30, "documentNumber");
        this.firstName = Guard.maxLength(Guard.notBlank(firstName, "firstName"), 60, "firstName");
        this.lastName = Guard.maxLength(Guard.notBlank(lastName, "lastName"), 60, "lastName");
        this.professionalType = Guard.notNull(professionalType, "professionalType");
        this.licenseNumber = Guard.maxLength(Guard.notBlank(licenseNumber, "licenseNumber"), 100, "licenseNumber");
        this.active = Guard.notNull(active, "active");
        this.cityId = Guard.notNull(cityId, "cityId");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ProfessionalUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ProfessionalDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ProfessionalId id() {
        return id;
    }

    public UUID documentTypeId() {
        return documentTypeId;
    }

    public String documentNumber() {
        return documentNumber;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public UUID professionalType() {
        return professionalType;
    }

    public String licenseNumber() {
        return licenseNumber;
    }

    public Boolean active() {
        return active;
    }

    public UUID cityId() {
        return cityId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
