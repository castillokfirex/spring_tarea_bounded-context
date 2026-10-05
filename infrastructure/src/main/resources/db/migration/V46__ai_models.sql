CREATE TABLE ai_models (
    id                   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    provider_model_id    VARCHAR(50)  NOT NULL, -- (revisar) FK en el diagrama, pero el tipo no coincide con provider_models_ai.id (UUID); ver nota arriba
    name_model           VARCHAR(100) NOT NULL,
    model_key            VARCHAR(170) NOT NULL,
    input_token_price    DECIMAL(12,8) NOT NULL,
    output_token_price   DECIMAL(12,8) NOT NULL,
    max_tokens           INTEGER      NOT NULL,
    context_window       INTEGER      NOT NULL,
    is_active            BOOLEAN      NOT NULL,
    created_at           TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at           TIMESTAMP    NOT NULL DEFAULT now()
);

