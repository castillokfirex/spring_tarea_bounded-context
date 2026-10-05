CREATE TABLE treatment_plans (
    id                   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    encounter_id         UUID         NOT NULL REFERENCES encounters(id),
    professional_id      UUID         NOT NULL REFERENCES professionals(id),
    title                VARCHAR(200) NOT NULL,
    description          TEXT         NOT NULL,
    start_date           DATE         NOT NULL,
    end_date             DATE         NOT NULL,
    treatment_status_id  UUID         NOT NULL REFERENCES treatment_statusses(id),
    created_at           TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at           TIMESTAMP    NOT NULL DEFAULT now()
);

