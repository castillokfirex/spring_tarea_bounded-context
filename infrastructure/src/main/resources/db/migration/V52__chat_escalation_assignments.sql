CREATE TABLE chat_escalation_assignments (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    escalation_id   UUID NOT NULL REFERENCES chat_escalations(id),
    professional_id UUID NOT NULL REFERENCES professionals(id),
    assigned_at     TIMESTAMP NOT NULL DEFAULT now()
);

