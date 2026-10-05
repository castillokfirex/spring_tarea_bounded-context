package com.example.tarea.domain.professionalstudy.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.professionalstudy.event.ProfessionalStudyDeletedEvent;
import com.example.tarea.domain.professionalstudy.event.ProfessionalStudyRegisteredEvent;
import com.example.tarea.domain.professionalstudy.event.ProfessionalStudyUpdatedEvent;
import com.example.tarea.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

/**
 * Aggregate root del bounded context <b>professionalstudy</b> (tabla <code>professional_studies</code>).
 */
public class ProfessionalStudy extends AggregateRoot {

    private final ProfessionalStudyId id;
    private UUID studyId;
    private UUID professionalId;
    private String title;
    private String university;
    private Boolean isValid;
    private String resolutionNumber;
    private UUID countryId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProfessionalStudy(
            ProfessionalStudyId id,
            UUID studyId,
            UUID professionalId,
            String title,
            String university,
            Boolean isValid,
            String resolutionNumber,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Guard.notNull(id, "id");
        this.studyId = Guard.notNull(studyId, "studyId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.title = Guard.maxLength(Guard.notBlank(title, "title"), 100, "title");
        this.university = Guard.maxLength(Guard.notBlank(university, "university"), 100, "university");
        this.isValid = Guard.notNull(isValid, "isValid");
        this.resolutionNumber = Guard.maxLength(resolutionNumber, 60, "resolutionNumber");
        this.countryId = Guard.notNull(countryId, "countryId");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.updatedAt = Guard.notNull(updatedAt, "updatedAt");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static ProfessionalStudy register(
            UUID studyId,
            UUID professionalId,
            String title,
            String university,
            Boolean isValid,
            String resolutionNumber,
            UUID countryId) {

        LocalDateTime now = LocalDateTime.now();
        ProfessionalStudy aggregate = new ProfessionalStudy(
                ProfessionalStudyId.generate(),
                studyId,
                professionalId,
                title,
                university,
                isValid,
                resolutionNumber,
                countryId,
                now,
                now);

        aggregate.recordEvent(new ProfessionalStudyRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static ProfessionalStudy restore(
            ProfessionalStudyId id,
            UUID studyId,
            UUID professionalId,
            String title,
            String university,
            Boolean isValid,
            String resolutionNumber,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ProfessionalStudy(
                id,
                studyId,
                professionalId,
                title,
                university,
                isValid,
                resolutionNumber,
                countryId,
                createdAt,
                updatedAt);
    }

    public void update(
            UUID studyId,
            UUID professionalId,
            String title,
            String university,
            Boolean isValid,
            String resolutionNumber,
            UUID countryId) {

        this.studyId = Guard.notNull(studyId, "studyId");
        this.professionalId = Guard.notNull(professionalId, "professionalId");
        this.title = Guard.maxLength(Guard.notBlank(title, "title"), 100, "title");
        this.university = Guard.maxLength(Guard.notBlank(university, "university"), 100, "university");
        this.isValid = Guard.notNull(isValid, "isValid");
        this.resolutionNumber = Guard.maxLength(resolutionNumber, 60, "resolutionNumber");
        this.countryId = Guard.notNull(countryId, "countryId");
        this.updatedAt = LocalDateTime.now();

        recordEvent(new ProfessionalStudyUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new ProfessionalStudyDeletedEvent(this.id, LocalDateTime.now()));
    }

    public ProfessionalStudyId id() {
        return id;
    }

    public UUID studyId() {
        return studyId;
    }

    public UUID professionalId() {
        return professionalId;
    }

    public String title() {
        return title;
    }

    public String university() {
        return university;
    }

    public Boolean isValid() {
        return isValid;
    }

    public String resolutionNumber() {
        return resolutionNumber;
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
