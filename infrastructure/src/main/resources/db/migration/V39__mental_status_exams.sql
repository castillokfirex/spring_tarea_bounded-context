CREATE TABLE mental_status_exams (
    id                     UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    encounter_id           UUID      NOT NULL REFERENCES encounters(id),
    appearance             TEXT      NOT NULL,
    behavior               TEXT      NOT NULL,
    attitude               TEXT      NOT NULL,
    consciousness          TEXT      NOT NULL,
    orientation            TEXT      NOT NULL,
    attention              TEXT      NOT NULL,
    memory                 TEXT      NOT NULL,
    speech                 TEXT      NOT NULL,
    mood                   TEXT      NOT NULL,
    affect                 TEXT      NOT NULL,
    thought_process        TEXT      NOT NULL,
    thought_content        TEXT      NOT NULL,
    perception             TEXT      NOT NULL,
    judgment               TEXT      NOT NULL,
    insight                TEXT      NOT NULL,
    psychomotor_activity   TEXT      NOT NULL,
    observations           TEXT      NOT NULL,
    created_at             TIMESTAMP NOT NULL DEFAULT now(),
    created_by             UUID      NOT NULL REFERENCES professionals(id)
);

