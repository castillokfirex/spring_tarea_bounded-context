package com.example.tarea.domain.patientcontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.patientcontact.event.PatientContactDeletedEvent;
import com.example.tarea.domain.patientcontact.event.PatientContactRegisteredEvent;
import com.example.tarea.domain.patientcontact.event.PatientContactUpdatedEvent;
import com.example.tarea.domain.patientcontact.model.valueobject.PatientContactId;

/**
 * Aggregate root del bounded context <b>patientcontact</b> (tabla <code>patient_contacts</code>).
 */
public class PatientContact extends AggregateRoot {

    private final PatientContactId id;
    private UUID contactId;
    private UUID patientId;
    private Boolean isPrimaryContact;
    private Boolean isEmergencyContact;
    private UUID relationshipTypeId;

    private PatientContact(
            PatientContactId id,
            UUID contactId,
            UUID patientId,
            Boolean isPrimaryContact,
            Boolean isEmergencyContact,
            UUID relationshipTypeId) {
        this.id = Guard.notNull(id, "id");
        this.contactId = Guard.notNull(contactId, "contactId");
        this.patientId = Guard.notNull(patientId, "patientId");
        this.isPrimaryContact = Guard.notNull(isPrimaryContact, "isPrimaryContact");
        this.isEmergencyContact = Guard.notNull(isEmergencyContact, "isEmergencyContact");
        this.relationshipTypeId = Guard.notNull(relationshipTypeId, "relationshipTypeId");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static PatientContact register(
            UUID contactId,
            UUID patientId,
            Boolean isPrimaryContact,
            Boolean isEmergencyContact,
            UUID relationshipTypeId) {

        LocalDateTime now = LocalDateTime.now();
        PatientContact aggregate = new PatientContact(
                PatientContactId.generate(),
                contactId,
                patientId,
                isPrimaryContact,
                isEmergencyContact,
                relationshipTypeId);

        aggregate.recordEvent(new PatientContactRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static PatientContact restore(
            PatientContactId id,
            UUID contactId,
            UUID patientId,
            Boolean isPrimaryContact,
            Boolean isEmergencyContact,
            UUID relationshipTypeId) {
        return new PatientContact(
                id,
                contactId,
                patientId,
                isPrimaryContact,
                isEmergencyContact,
                relationshipTypeId);
    }

    public void update(
            UUID contactId,
            UUID patientId,
            Boolean isPrimaryContact,
            Boolean isEmergencyContact,
            UUID relationshipTypeId) {

        this.contactId = Guard.notNull(contactId, "contactId");
        this.patientId = Guard.notNull(patientId, "patientId");
        this.isPrimaryContact = Guard.notNull(isPrimaryContact, "isPrimaryContact");
        this.isEmergencyContact = Guard.notNull(isEmergencyContact, "isEmergencyContact");
        this.relationshipTypeId = Guard.notNull(relationshipTypeId, "relationshipTypeId");

        recordEvent(new PatientContactUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new PatientContactDeletedEvent(this.id, LocalDateTime.now()));
    }

    public PatientContactId id() {
        return id;
    }

    public UUID contactId() {
        return contactId;
    }

    public UUID patientId() {
        return patientId;
    }

    public Boolean isPrimaryContact() {
        return isPrimaryContact;
    }

    public Boolean isEmergencyContact() {
        return isEmergencyContact;
    }

    public UUID relationshipTypeId() {
        return relationshipTypeId;
    }
}
