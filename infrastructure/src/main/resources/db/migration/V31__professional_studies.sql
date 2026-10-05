CREATE TABLE professional_studies (
    id                   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    study_id             UUID         NOT NULL REFERENCES studies(id),
    professional_id      UUID         NOT NULL REFERENCES professionals(id),
    title                VARCHAR(100) NOT NULL,
    university           VARCHAR(100) NOT NULL,
    is_valid             BOOLEAN      NOT NULL,
    resolution_number    VARCHAR(60),
    country_id           UUID         NOT NULL REFERENCES countries(id),
    created_at           TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at           TIMESTAMP    NOT NULL DEFAULT now()
);

