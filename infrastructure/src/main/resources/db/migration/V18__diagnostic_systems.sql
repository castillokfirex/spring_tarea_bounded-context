CREATE TABLE diagnostic_systems (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code        VARCHAR(20) NOT NULL UNIQUE,  -- p.ej. "CIE-10", "DSM-5"
    name        VARCHAR(50) NOT NULL,
    active      BOOLEAN     NOT NULL,
    version     VARCHAR(20),
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP   NOT NULL DEFAULT now()
);

