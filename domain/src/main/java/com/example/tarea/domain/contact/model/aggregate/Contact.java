package com.example.tarea.domain.contact.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.contact.event.ContactDeletedEvent;
import com.example.tarea.domain.contact.event.ContactRegisteredEvent;
import com.example.tarea.domain.contact.event.ContactUpdatedEvent;
import com.example.tarea.domain.contact.model.valueobject.ContactId;

/**
 * Aggregate root del bounded context <b>contact</b> (tabla <code>contacts</code>).
 */
public class Contact extends AggregateRoot {

    private final ContactId id;
    private String fullName;
    private String email;
    private String notes;
    private UUID cityId;
    private final LocalDateTime createdAt;
    private final UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;

    private Contact(
            ContactId id,
            String fullName,
            String email,
            String notes,
            UUID cityId,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy) {
        this.id = Guard.notNull(id, "id");
        this.fullName = Guard.maxLength(Guard.notBlank(fullName, "fullName"), 200, "fullName");
        this.email = Guard.maxLength(Guard.notBlank(email, "email"), 150, "email");
        this.notes = Guard.notBlank(notes, "notes");
        this.cityId = Guard.notNull(cityId, "cityId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.createdBy = Guard.notNull(createdBy, "createdBy");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
        this.updatedBy = updatedBy;
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static Contact register(
            String fullName,
            String email,
            String notes,
            UUID cityId,
            UUID createdBy,
            UUID updatedBy) {

        LocalDateTime now = LocalDateTime.now();
        Contact aggregate = new Contact(
                ContactId.generate(),
                fullName,
                email,
                notes,
                cityId,
                now,
                createdBy,
                now,
                updatedBy);

        aggregate.recordEvent(new ContactRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static Contact restore(
            ContactId id,
            String fullName,
            String email,
            String notes,
            UUID cityId,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy) {
        return new Contact(
                id,
                fullName,
                email,
                notes,
                cityId,
                createdAt,
                createdBy,
                updatedAt,
                updatedBy);
    }

    public void update(
            String fullName,
            String email,
            String notes,
            UUID cityId,
            UUID updatedBy) {

        this.fullName = Guard.maxLength(Guard.notBlank(fullName, "fullName"), 200, "fullName");
        this.email = Guard.maxLength(Guard.notBlank(email, "email"), 150, "email");
        this.notes = Guard.notBlank(notes, "notes");
        this.cityId = Guard.notNull(cityId, "cityId");
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ContactUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ContactDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ContactId id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public String email() {
        return email;
    }

    public String notes() {
        return notes;
    }

    public UUID cityId() {
        return cityId;
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
