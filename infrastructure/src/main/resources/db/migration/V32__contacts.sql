-- =====================================================================
-- 4. CONTACTOS
-- =====================================================================

CREATE TABLE contacts (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name   VARCHAR(200) NOT NULL,
    email       VARCHAR(150) NOT NULL,
    notes       TEXT         NOT NULL,
    city_id     UUID         NOT NULL REFERENCES city_municipalities(id),
    created_at  TIMESTAMP    NOT NULL DEFAULT now(),
    created_by  UUID         NOT NULL,
    updated_at  TIMESTAMP    NOT NULL DEFAULT now(),
    updated_by  UUID
);

