CREATE TABLE encounter_modalities (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code        VARCHAR(20) NOT NULL UNIQUE,
    name        VARCHAR(50) NOT NULL,
    active      BOOLEAN     NOT NULL,
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP   NOT NULL DEFAULT now()
);

