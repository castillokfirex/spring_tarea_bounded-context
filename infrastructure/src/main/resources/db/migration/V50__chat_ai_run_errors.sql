CREATE TABLE chat_ai_run_errors (
    id                   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ai_run_id            UUID NOT NULL REFERENCES chat_ai_runs(id),
    error_message        TEXT NOT NULL,
    error_code           VARCHAR(80)  NOT NULL,
    provider_error_id    VARCHAR(120) NOT NULL,
    created_at           TIMESTAMP NOT NULL DEFAULT now()
);

