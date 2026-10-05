CREATE TABLE relationship_types (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description VARCHAR(50) NOT NULL UNIQUE
);

