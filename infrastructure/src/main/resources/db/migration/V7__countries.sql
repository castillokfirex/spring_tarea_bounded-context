CREATE TABLE countries (
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name_country      VARCHAR(50)  NOT NULL,
    code_country      VARCHAR(10),
    description       VARCHAR(100),
    is_active         BOOLEAN      NOT NULL,
    telephone_prefix  VARCHAR(5),
    created_at        TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at        TIMESTAMP    NOT NULL DEFAULT now()
);

