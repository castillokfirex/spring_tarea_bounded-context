package com.example.tarea.domain.patient.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.patient.event.PatientDeletedEvent;
import com.example.tarea.domain.patient.event.PatientRegisteredEvent;
import com.example.tarea.domain.patient.event.PatientUpdatedEvent;
import com.example.tarea.domain.patient.model.valueobject.PatientId;

/**
 * Aggregate root del bounded context <b>patient</b> (tabla <code>patients</code>).
 */
public class Patient extends AggregateRoot {

    private final PatientId id;
    private UUID documentTypeId;
    private String documentNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthDate;
    private UUID biologicalSexId;
    private UUID genderIdentity;
    private String email;
    private String phone;
    private String address;
    private Boolean active;
    private final LocalDateTime createdAt;
    private final UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
    private UUID cityId;

    private Patient(
            PatientId id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            UUID biologicalSexId,
            UUID genderIdentity,
            String email,
            String phone,
            String address,
            Boolean active,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy,
            UUID cityId) {
        this.id = Guard.notNull(id, "id");
        this.documentTypeId = Guard.notNull(documentTypeId, "documentTypeId");
        this.documentNumber = Guard.maxLength(Guard.notBlank(documentNumber, "documentNumber"), 30, "documentNumber");
        this.firstName = Guard.maxLength(Guard.notBlank(firstName, "firstName"), 50, "firstName");
        this.middleName = Guard.maxLength(middleName, 50, "middleName");
        this.lastName = Guard.maxLength(Guard.notBlank(lastName, "lastName"), 50, "lastName");
        this.secondLastName = Guard.maxLength(secondLastName, 50, "secondLastName");
        this.birthDate = Guard.notNull(birthDate, "birthDate");
        this.biologicalSexId = Guard.notNull(biologicalSexId, "biologicalSexId");
        this.genderIdentity = Guard.notNull(genderIdentity, "genderIdentity");
        this.email = Guard.maxLength(Guard.notBlank(email, "email"), 150, "email");
        this.phone = Guard.maxLength(Guard.notBlank(phone, "phone"), 30, "phone");
        this.address = Guard.maxLength(Guard.notBlank(address, "address"), 250, "address");
        this.active = Guard.notNull(active, "active");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.createdBy = createdBy;
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
        this.updatedBy = updatedBy;
        this.cityId = Guard.notNull(cityId, "cityId");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static Patient register(
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            UUID biologicalSexId,
            UUID genderIdentity,
            String email,
            String phone,
            String address,
            Boolean active,
            UUID createdBy,
            UUID updatedBy,
            UUID cityId) {

        LocalDateTime now = LocalDateTime.now();
        Patient aggregate = new Patient(
                PatientId.generate(),
                documentTypeId,
                documentNumber,
                firstName,
                middleName,
                lastName,
                secondLastName,
                birthDate,
                biologicalSexId,
                genderIdentity,
                email,
                phone,
                address,
                active,
                now,
                createdBy,
                now,
                updatedBy,
                cityId);

        aggregate.recordEvent(new PatientRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static Patient restore(
            PatientId id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            UUID biologicalSexId,
            UUID genderIdentity,
            String email,
            String phone,
            String address,
            Boolean active,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy,
            UUID cityId) {
        return new Patient(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                middleName,
                lastName,
                secondLastName,
                birthDate,
                biologicalSexId,
                genderIdentity,
                email,
                phone,
                address,
                active,
                createdAt,
                createdBy,
                updatedAt,
                updatedBy,
                cityId);
    }

    public void update(
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            UUID biologicalSexId,
            UUID genderIdentity,
            String email,
            String phone,
            String address,
            Boolean active,
            UUID updatedBy,
            UUID cityId) {

        this.documentTypeId = Guard.notNull(documentTypeId, "documentTypeId");
        this.documentNumber = Guard.maxLength(Guard.notBlank(documentNumber, "documentNumber"), 30, "documentNumber");
        this.firstName = Guard.maxLength(Guard.notBlank(firstName, "firstName"), 50, "firstName");
        this.middleName = Guard.maxLength(middleName, 50, "middleName");
        this.lastName = Guard.maxLength(Guard.notBlank(lastName, "lastName"), 50, "lastName");
        this.secondLastName = Guard.maxLength(secondLastName, 50, "secondLastName");
        this.birthDate = Guard.notNull(birthDate, "birthDate");
        this.biologicalSexId = Guard.notNull(biologicalSexId, "biologicalSexId");
        this.genderIdentity = Guard.notNull(genderIdentity, "genderIdentity");
        this.email = Guard.maxLength(Guard.notBlank(email, "email"), 150, "email");
        this.phone = Guard.maxLength(Guard.notBlank(phone, "phone"), 30, "phone");
        this.address = Guard.maxLength(Guard.notBlank(address, "address"), 250, "address");
        this.active = Guard.notNull(active, "active");
        this.updatedBy = updatedBy;
        this.cityId = Guard.notNull(cityId, "cityId");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new PatientUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new PatientDeletedEvent(this.id, LocalDateTime.now()));
    }

    public PatientId id() {
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

    public String middleName() {
        return middleName;
    }

    public String lastName() {
        return lastName;
    }

    public String secondLastName() {
        return secondLastName;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public UUID biologicalSexId() {
        return biologicalSexId;
    }

    public UUID genderIdentity() {
        return genderIdentity;
    }

    public String email() {
        return email;
    }

    public String phone() {
        return phone;
    }

    public String address() {
        return address;
    }

    public Boolean active() {
        return active;
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

    public UUID cityId() {
        return cityId;
    }
}
