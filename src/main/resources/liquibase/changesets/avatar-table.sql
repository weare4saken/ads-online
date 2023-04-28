-- liquibase formatted sql

-- changeset weare4saken:1
CREATE TABLE IF NOT EXISTS avatar(
    id              SERIAL PRIMARY KEY,
    media_type      VARCHAR(255),
    file_size       BIGINT,
    data            BYTEA
);