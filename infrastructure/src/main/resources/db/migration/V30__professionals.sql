CREATE TABLE professionals (
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    document_type_id   UUID         NOT NULL REFERENCES document_types(id),
    document_number    VARCHAR(30)  NOT NULL UNIQUE,
    first_name         VARCHAR(60)  NOT NULL UNIQUE, -- (revisar) único por diagrama; raro a nivel de diseño
    last_name          VARCHAR(60)  NOT NULL UNIQUE, -- (revisar) único por diagrama; raro a nivel de diseño
    professional_type  UUID         NOT NULL REFERENCES professional_types(id),
    license_number     VARCHAR(100) NOT NULL UNIQUE,
    active             BOOLEAN      NOT NULL,
    city_id            UUID         NOT NULL REFERENCES city_municipalities(id),
    created_at         TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at         TIMESTAMP    NOT NULL DEFAULT now()
);

