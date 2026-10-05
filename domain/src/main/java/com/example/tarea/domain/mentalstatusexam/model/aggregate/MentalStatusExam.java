package com.example.tarea.domain.mentalstatusexam.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.mentalstatusexam.event.MentalStatusExamDeletedEvent;
import com.example.tarea.domain.mentalstatusexam.event.MentalStatusExamRegisteredEvent;
import com.example.tarea.domain.mentalstatusexam.event.MentalStatusExamUpdatedEvent;
import com.example.tarea.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

/**
 * Aggregate root del bounded context <b>mentalstatusexam</b> (tabla <code>mental_status_exams</code>).
 */
public class MentalStatusExam extends AggregateRoot {

    private final MentalStatusExamId id;
    private UUID encounterId;
    private String appearance;
    private String behavior;
    private String attitude;
    private String consciousness;
    private String orientation;
    private String attention;
    private String memory;
    private String speech;
    private String mood;
    private String affect;
    private String thoughtProcess;
    private String thoughtContent;
    private String perception;
    private String judgment;
    private String insight;
    private String psychomotorActivity;
    private String observations;
    private final LocalDateTime createdAt;
    private final UUID createdBy;

    private MentalStatusExam(
            MentalStatusExamId id,
            UUID encounterId,
            String appearance,
            String behavior,
            String attitude,
            String consciousness,
            String orientation,
            String attention,
            String memory,
            String speech,
            String mood,
            String affect,
            String thoughtProcess,
            String thoughtContent,
            String perception,
            String judgment,
            String insight,
            String psychomotorActivity,
            String observations,
            LocalDateTime createdAt,
            UUID createdBy) {
        this.id = Guard.notNull(id, "id");
        this.encounterId = Guard.notNull(encounterId, "encounterId");
        this.appearance = Guard.notBlank(appearance, "appearance");
        this.behavior = Guard.notBlank(behavior, "behavior");
        this.attitude = Guard.notBlank(attitude, "attitude");
        this.consciousness = Guard.notBlank(consciousness, "consciousness");
        this.orientation = Guard.notBlank(orientation, "orientation");
        this.attention = Guard.notBlank(attention, "attention");
        this.memory = Guard.notBlank(memory, "memory");
        this.speech = Guard.notBlank(speech, "speech");
        this.mood = Guard.notBlank(mood, "mood");
        this.affect = Guard.notBlank(affect, "affect");
        this.thoughtProcess = Guard.notBlank(thoughtProcess, "thoughtProcess");
        this.thoughtContent = Guard.notBlank(thoughtContent, "thoughtContent");
        this.perception = Guard.notBlank(perception, "perception");
        this.judgment = Guard.notBlank(judgment, "judgment");
        this.insight = Guard.notBlank(insight, "insight");
        this.psychomotorActivity = Guard.notBlank(psychomotorActivity, "psychomotorActivity");
        this.observations = Guard.notBlank(observations, "observations");
        this.createdAt = Guard.notNull(createdAt, "createdAt");
        this.createdBy = Guard.notNull(createdBy, "createdBy");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static MentalStatusExam register(
            UUID encounterId,
            String appearance,
            String behavior,
            String attitude,
            String consciousness,
            String orientation,
            String attention,
            String memory,
            String speech,
            String mood,
            String affect,
            String thoughtProcess,
            String thoughtContent,
            String perception,
            String judgment,
            String insight,
            String psychomotorActivity,
            String observations,
            UUID createdBy) {

        LocalDateTime now = LocalDateTime.now();
        MentalStatusExam aggregate = new MentalStatusExam(
                MentalStatusExamId.generate(),
                encounterId,
                appearance,
                behavior,
                attitude,
                consciousness,
                orientation,
                attention,
                memory,
                speech,
                mood,
                affect,
                thoughtProcess,
                thoughtContent,
                perception,
                judgment,
                insight,
                psychomotorActivity,
                observations,
                now,
                createdBy);

        aggregate.recordEvent(new MentalStatusExamRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static MentalStatusExam restore(
            MentalStatusExamId id,
            UUID encounterId,
            String appearance,
            String behavior,
            String attitude,
            String consciousness,
            String orientation,
            String attention,
            String memory,
            String speech,
            String mood,
            String affect,
            String thoughtProcess,
            String thoughtContent,
            String perception,
            String judgment,
            String insight,
            String psychomotorActivity,
            String observations,
            LocalDateTime createdAt,
            UUID createdBy) {
        return new MentalStatusExam(
                id,
                encounterId,
                appearance,
                behavior,
                attitude,
                consciousness,
                orientation,
                attention,
                memory,
                speech,
                mood,
                affect,
                thoughtProcess,
                thoughtContent,
                perception,
                judgment,
                insight,
                psychomotorActivity,
                observations,
                createdAt,
                createdBy);
    }

    public void update(
            UUID encounterId,
            String appearance,
            String behavior,
            String attitude,
            String consciousness,
            String orientation,
            String attention,
            String memory,
            String speech,
            String mood,
            String affect,
            String thoughtProcess,
            String thoughtContent,
            String perception,
            String judgment,
            String insight,
            String psychomotorActivity,
            String observations) {

        this.encounterId = Guard.notNull(encounterId, "encounterId");
        this.appearance = Guard.notBlank(appearance, "appearance");
        this.behavior = Guard.notBlank(behavior, "behavior");
        this.attitude = Guard.notBlank(attitude, "attitude");
        this.consciousness = Guard.notBlank(consciousness, "consciousness");
        this.orientation = Guard.notBlank(orientation, "orientation");
        this.attention = Guard.notBlank(attention, "attention");
        this.memory = Guard.notBlank(memory, "memory");
        this.speech = Guard.notBlank(speech, "speech");
        this.mood = Guard.notBlank(mood, "mood");
        this.affect = Guard.notBlank(affect, "affect");
        this.thoughtProcess = Guard.notBlank(thoughtProcess, "thoughtProcess");
        this.thoughtContent = Guard.notBlank(thoughtContent, "thoughtContent");
        this.perception = Guard.notBlank(perception, "perception");
        this.judgment = Guard.notBlank(judgment, "judgment");
        this.insight = Guard.notBlank(insight, "insight");
        this.psychomotorActivity = Guard.notBlank(psychomotorActivity, "psychomotorActivity");
        this.observations = Guard.notBlank(observations, "observations");

        recordEvent(new MentalStatusExamUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new MentalStatusExamDeletedEvent(this.id, LocalDateTime.now()));
    }

    public MentalStatusExamId id() {
        return id;
    }

    public UUID encounterId() {
        return encounterId;
    }

    public String appearance() {
        return appearance;
    }

    public String behavior() {
        return behavior;
    }

    public String attitude() {
        return attitude;
    }

    public String consciousness() {
        return consciousness;
    }

    public String orientation() {
        return orientation;
    }

    public String attention() {
        return attention;
    }

    public String memory() {
        return memory;
    }

    public String speech() {
        return speech;
    }

    public String mood() {
        return mood;
    }

    public String affect() {
        return affect;
    }

    public String thoughtProcess() {
        return thoughtProcess;
    }

    public String thoughtContent() {
        return thoughtContent;
    }

    public String perception() {
        return perception;
    }

    public String judgment() {
        return judgment;
    }

    public String insight() {
        return insight;
    }

    public String psychomotorActivity() {
        return psychomotorActivity;
    }

    public String observations() {
        return observations;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public UUID createdBy() {
        return createdBy;
    }
}
