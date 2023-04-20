-- liquibase formatted sql

-- changeset weare4saken:2
CREATE TABLE IF NOT EXISTS image(
    id              SERIAL PRIMARY KEY,
    media_type      VARCHAR(255),
    file_size       BIGINT,
    data            BYTEA
);