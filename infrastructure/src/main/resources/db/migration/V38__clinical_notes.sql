CREATE TABLE clinical_notes ( -- formato SOAP
    id               UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    encounter_id     UUID      NOT NULL REFERENCES encounters(id),
    professional_id  UUID      NOT NULL REFERENCES professionals(id),
    subjective       TEXT      NOT NULL,
    objective        TEXT      NOT NULL,
    assessment       TEXT      NOT NULL,
    plan             TEXT      NOT NULL,
    additional_notes TEXT      NOT NULL,
    signed_at        TIMESTAMP NOT NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT now(),
    updated_at       TIMESTAMP NOT NULL DEFAULT now()
);

