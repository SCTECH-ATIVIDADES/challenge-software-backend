-- Create cities table
CREATE TABLE cities (
    id BIGSERIAL PRIMARY KEY,
    code INTEGER NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL
);