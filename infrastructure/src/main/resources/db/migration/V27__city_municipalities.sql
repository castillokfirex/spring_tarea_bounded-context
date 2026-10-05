CREATE TABLE city_municipalities (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name_city    VARCHAR(50)  NOT NULL,
    code_city    VARCHAR(10), -- (revisar) se ve como "code_citi" en el diagrama
    description  VARCHAR(100),
    is_active    BOOLEAN      NOT NULL,
    region_id    UUID         NOT NULL REFERENCES state_regions(id),
    created_at   TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP    NOT NULL DEFAULT now()
);

