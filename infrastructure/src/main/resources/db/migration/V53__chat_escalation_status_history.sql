CREATE TABLE chat_escalation_status_history (
    id                     UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    escalation_id          UUID NOT NULL REFERENCES chat_escalations(id),
    escalation_status_id   UUID NOT NULL REFERENCES escalations_statuses(id),
    created_at             TIMESTAMP NOT NULL DEFAULT now(),
    changed_at             TIMESTAMP NOT NULL
);

