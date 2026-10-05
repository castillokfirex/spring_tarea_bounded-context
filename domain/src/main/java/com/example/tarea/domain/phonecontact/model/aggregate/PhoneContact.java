package com.example.tarea.domain.phonecontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.phonecontact.event.PhoneContactDeletedEvent;
import com.example.tarea.domain.phonecontact.event.PhoneContactRegisteredEvent;
import com.example.tarea.domain.phonecontact.event.PhoneContactUpdatedEvent;
import com.example.tarea.domain.phonecontact.model.valueobject.PhoneContactId;

/**
 * Aggregate root del bounded context <b>phonecontact</b> (tabla <code>phone_contacts</code>).
 */
public class PhoneContact extends AggregateRoot {

    private final PhoneContactId id;
    private UUID contactId;
    private String phone;
    private String notes;

    private PhoneContact(
            PhoneContactId id,
            UUID contactId,
            String phone,
            String notes) {
        this.id = Guard.notNull(id, "id");
        this.contactId = Guard.notNull(contactId, "contactId");
        this.phone = Guard.maxLength(Guard.notBlank(phone, "phone"), 30, "phone");
        this.notes = notes;
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static PhoneContact register(
            UUID contactId,
            String phone,
            String notes) {

        LocalDateTime now = LocalDateTime.now();
        PhoneContact aggregate = new PhoneContact(
                PhoneContactId.generate(),
                contactId,
                phone,
                notes);

        aggregate.recordEvent(new PhoneContactRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static PhoneContact restore(
            PhoneContactId id,
            UUID contactId,
            String phone,
            String notes) {
        return new PhoneContact(
                id,
                contactId,
                phone,
                notes);
    }

    public void update(
            UUID contactId,
            String phone,
            String notes) {

        this.contactId = Guard.notNull(contactId, "contactId");
        this.phone = Guard.maxLength(Guard.notBlank(phone, "phone"), 30, "phone");
        this.notes = notes;

        recordEvent(new PhoneContactUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new PhoneContactDeletedEvent(this.id, LocalDateTime.now()));
    }

    public PhoneContactId id() {
        return id;
    }

    public UUID contactId() {
        return contactId;
    }

    public String phone() {
        return phone;
    }

    public String notes() {
        return notes;
    }
}
