-- =====================================================================
-- 2. GEOGRAFÍA (país -> región -> ciudad)
-- =====================================================================

CREATE TABLE state_regions (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name_region  VARCHAR(50)  NOT NULL,
    code_region  VARCHAR(10),
    description  VARCHAR(100),
    is_active    BOOLEAN      NOT NULL,
    country_id   UUID         NOT NULL REFERENCES countries(id),
    created_at   TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP    NOT NULL DEFAULT now()
);

