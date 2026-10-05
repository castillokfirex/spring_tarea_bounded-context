-- =====================================================================
-- 3. PERSONAS
-- =====================================================================

CREATE TABLE patients (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    document_type_id    UUID         NOT NULL REFERENCES document_types(id),
    document_number     VARCHAR(30)  NOT NULL,
    first_name          VARCHAR(50)  NOT NULL,
    middle_name         VARCHAR(50),
    last_name           VARCHAR(50)  NOT NULL,
    second_last_name    VARCHAR(50),
    birth_date          DATE         NOT NULL,
    biological_sex_id   UUID         NOT NULL REFERENCES genders(id),
    gender_identity     UUID         NOT NULL REFERENCES genders(id),
    email               VARCHAR(150) NOT NULL UNIQUE,
    phone               VARCHAR(30)  NOT NULL,
    address              VARCHAR(250) NOT NULL,
    active              BOOLEAN      NOT NULL,
    created_at          TIMESTAMP    NOT NULL DEFAULT now(),
    created_by          UUID,
    updated_at          TIMESTAMP    NOT NULL DEFAULT now(),
    updated_by          UUID,
    city_id             UUID         NOT NULL REFERENCES city_municipalities(id),
    UNIQUE (document_type_id, document_number)
);

