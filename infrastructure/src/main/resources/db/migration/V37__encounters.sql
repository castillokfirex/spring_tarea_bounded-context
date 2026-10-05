CREATE TABLE encounters (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    clinical_record_id  UUID      NOT NULL REFERENCES clinical_records(id),
    professional_id     UUID      NOT NULL REFERENCES professionals(id),
    encounter_type_id   UUID      NOT NULL REFERENCES encounter_types(id),
    started_at          TIMESTAMP NOT NULL DEFAULT now(),
    ended_at            TIMESTAMP,
    reason_for_visit    TEXT      NOT NULL,
    current_condition   TEXT      NOT NULL,
    modality_id         UUID      NOT NULL REFERENCES encounter_modalities(id),
    status_id           UUID      NOT NULL REFERENCES encounter_statusses(id),
    created_at          TIMESTAMP NOT NULL DEFAULT now(),
    created_by          UUID      NOT NULL,
    updated_at          TIMESTAMP NOT NULL DEFAULT now(),
    updated_by          UUID      NOT NULL
);

