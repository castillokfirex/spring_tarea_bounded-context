CREATE TABLE risk_assessments (
    id                   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    encounter_id         UUID    NOT NULL REFERENCES encounters(id),
    risk_level_id        UUID    NOT NULL REFERENCES risk_levels(id),
    suicidal_ideation    BOOLEAN NOT NULL,
    suicide_plan         BOOLEAN NOT NULL,
    suicide_intent       BOOLEAN NOT NULL,
    self_harm            BOOLEAN NOT NULL,
    harm_to_others       BOOLEAN NOT NULL,
    risk_factors         TEXT    NOT NULL,
    protective_factors   TEXT    NOT NULL,
    clinical_actions     TEXT    NOT NULL,
    observations         TEXT    NOT NULL,
    assessed_at          TIMESTAMP NOT NULL DEFAULT now(),
    assessed_by          UUID    NOT NULL REFERENCES professionals(id)
);

