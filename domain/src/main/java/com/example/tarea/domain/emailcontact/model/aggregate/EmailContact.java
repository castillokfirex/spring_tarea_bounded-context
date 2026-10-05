package com.example.tarea.domain.emailcontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.emailcontact.event.EmailContactDeletedEvent;
import com.example.tarea.domain.emailcontact.event.EmailContactRegisteredEvent;
import com.example.tarea.domain.emailcontact.event.EmailContactUpdatedEvent;
import com.example.tarea.domain.emailcontact.model.valueobject.EmailContactId;

/**
 * Aggregate root del bounded context <b>emailcontact</b> (tabla <code>email_contacts</code>).
 */
public class EmailContact extends AggregateRoot {

    private final EmailContactId id;
    private UUID contactId;
    private String email;
    private String notes;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EmailContact(
            EmailContactId id,
            UUID contactId,
            String email,
            String notes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.contactId = Guard.notNull(contactId, "contactId");
        this.email = Guard.maxLength(Guard.notBlank(email, "email"), 150, "email");
        this.notes = Guard.notBlank(notes, "notes");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static EmailContact register(
            UUID contactId,
            String email,
            String notes) {

        LocalDateTime now = LocalDateTime.now();
        EmailContact aggregate = new EmailContact(
                EmailContactId.generate(),
                contactId,
                email,
                notes,
                now,
                now);

        aggregate.recordEvent(new EmailContactRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static EmailContact restore(
            EmailContactId id,
            UUID contactId,
            String email,
            String notes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EmailContact(
                id,
                contactId,
                email,
                notes,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID contactId,
            String email,
            String notes) {

        this.contactId = Guard.notNull(contactId, "contactId");
        this.email = Guard.maxLength(Guard.notBlank(email, "email"), 150, "email");
        this.notes = Guard.notBlank(notes, "notes");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new EmailContactUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new EmailContactDeletedEvent(this.id, LocalDateTime.now()));
    }

    public EmailContactId id() {
        return id;
    }

    public UUID contactId() {
        return contactId;
    }

    public String email() {
        return email;
    }

    public String notes() {
        return notes;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
