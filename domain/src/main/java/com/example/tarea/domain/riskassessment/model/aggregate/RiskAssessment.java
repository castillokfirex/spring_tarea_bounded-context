package com.example.tarea.domain.riskassessment.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.common.model.AggregateRoot;
import com.example.tarea.domain.common.validation.Guard;
import com.example.tarea.domain.riskassessment.event.RiskAssessmentDeletedEvent;
import com.example.tarea.domain.riskassessment.event.RiskAssessmentRegisteredEvent;
import com.example.tarea.domain.riskassessment.event.RiskAssessmentUpdatedEvent;
import com.example.tarea.domain.riskassessment.model.valueobject.RiskAssessmentId;

/**
 * Aggregate root del bounded context <b>riskassessment</b> (tabla <code>risk_assessments</code>).
 */
public class RiskAssessment extends AggregateRoot {

    private final RiskAssessmentId id;
    private UUID encounterId;
    private UUID riskLevelId;
    private Boolean suicidalIdeation;
    private Boolean suicidePlan;
    private Boolean suicideIntent;
    private Boolean selfHarm;
    private Boolean harmToOthers;
    private String riskFactors;
    private String protectiveFactors;
    private String clinicalActions;
    private String observations;
    private LocalDateTime assessedAt;
    private UUID assessedBy;

    private RiskAssessment(
            RiskAssessmentId id,
            UUID encounterId,
            UUID riskLevelId,
            Boolean suicidalIdeation,
            Boolean suicidePlan,
            Boolean suicideIntent,
            Boolean selfHarm,
            Boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            UUID assessedBy) {
        this.id = Guard.notNull(id, "id");
        this.encounterId = Guard.notNull(encounterId, "encounterId");
        this.riskLevelId = Guard.notNull(riskLevelId, "riskLevelId");
        this.suicidalIdeation = Guard.notNull(suicidalIdeation, "suicidalIdeation");
        this.suicidePlan = Guard.notNull(suicidePlan, "suicidePlan");
        this.suicideIntent = Guard.notNull(suicideIntent, "suicideIntent");
        this.selfHarm = Guard.notNull(selfHarm, "selfHarm");
        this.harmToOthers = Guard.notNull(harmToOthers, "harmToOthers");
        this.riskFactors = Guard.notBlank(riskFactors, "riskFactors");
        this.protectiveFactors = Guard.notBlank(protectiveFactors, "protectiveFactors");
        this.clinicalActions = Guard.notBlank(clinicalActions, "clinicalActions");
        this.observations = Guard.notBlank(observations, "observations");
        this.assessedAt = Guard.notNull(assessedAt, "assessedAt");
        this.assessedBy = Guard.notNull(assessedBy, "assessedBy");
    }

    /** Crea un nuevo agregado y registra el evento de dominio correspondiente. */
    public static RiskAssessment register(
            UUID encounterId,
            UUID riskLevelId,
            Boolean suicidalIdeation,
            Boolean suicidePlan,
            Boolean suicideIntent,
            Boolean selfHarm,
            Boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            UUID assessedBy) {

        LocalDateTime now = LocalDateTime.now();
        RiskAssessment aggregate = new RiskAssessment(
                RiskAssessmentId.generate(),
                encounterId,
                riskLevelId,
                suicidalIdeation,
                suicidePlan,
                suicideIntent,
                selfHarm,
                harmToOthers,
                riskFactors,
                protectiveFactors,
                clinicalActions,
                observations,
                assessedAt != null ? assessedAt : now,
                assessedBy);

        aggregate.recordEvent(new RiskAssessmentRegisteredEvent(aggregate.id, now));
        return aggregate;
    }

    /** Reconstruye el agregado desde persistencia (no genera eventos). */
    public static RiskAssessment restore(
            RiskAssessmentId id,
            UUID encounterId,
            UUID riskLevelId,
            Boolean suicidalIdeation,
            Boolean suicidePlan,
            Boolean suicideIntent,
            Boolean selfHarm,
            Boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            UUID assessedBy) {
        return new RiskAssessment(
                id,
                encounterId,
                riskLevelId,
                suicidalIdeation,
                suicidePlan,
                suicideIntent,
                selfHarm,
                harmToOthers,
                riskFactors,
                protectiveFactors,
                clinicalActions,
                observations,
                assessedAt,
                assessedBy);
    }

    public void update(
            UUID encounterId,
            UUID riskLevelId,
            Boolean suicidalIdeation,
            Boolean suicidePlan,
            Boolean suicideIntent,
            Boolean selfHarm,
            Boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            UUID assessedBy) {

        this.encounterId = Guard.notNull(encounterId, "encounterId");
        this.riskLevelId = Guard.notNull(riskLevelId, "riskLevelId");
        this.suicidalIdeation = Guard.notNull(suicidalIdeation, "suicidalIdeation");
        this.suicidePlan = Guard.notNull(suicidePlan, "suicidePlan");
        this.suicideIntent = Guard.notNull(suicideIntent, "suicideIntent");
        this.selfHarm = Guard.notNull(selfHarm, "selfHarm");
        this.harmToOthers = Guard.notNull(harmToOthers, "harmToOthers");
        this.riskFactors = Guard.notBlank(riskFactors, "riskFactors");
        this.protectiveFactors = Guard.notBlank(protectiveFactors, "protectiveFactors");
        this.clinicalActions = Guard.notBlank(clinicalActions, "clinicalActions");
        this.observations = Guard.notBlank(observations, "observations");
        this.assessedAt = assessedAt != null ? assessedAt : this.assessedAt;
        this.assessedBy = Guard.notNull(assessedBy, "assessedBy");

        recordEvent(new RiskAssessmentUpdatedEvent(this.id, LocalDateTime.now()));
    }

    /** Marca el agregado para eliminacion registrando el evento de dominio. */
    public void delete() {
        recordEvent(new RiskAssessmentDeletedEvent(this.id, LocalDateTime.now()));
    }

    public RiskAssessmentId id() {
        return id;
    }

    public UUID encounterId() {
        return encounterId;
    }

    public UUID riskLevelId() {
        return riskLevelId;
    }

    public Boolean suicidalIdeation() {
        return suicidalIdeation;
    }

    public Boolean suicidePlan() {
        return suicidePlan;
    }

    public Boolean suicideIntent() {
        return suicideIntent;
    }

    public Boolean selfHarm() {
        return selfHarm;
    }

    public Boolean harmToOthers() {
        return harmToOthers;
    }

    public String riskFactors() {
        return riskFactors;
    }

    public String protectiveFactors() {
        return protectiveFactors;
    }

    public String clinicalActions() {
        return clinicalActions;
    }

    public String observations() {
        return observations;
    }

    public LocalDateTime assessedAt() {
        return assessedAt;
    }

    public UUID assessedBy() {
        return assessedBy;
    }
}
