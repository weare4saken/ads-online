-- liquibase formatted sql

-- changeset weare4saken:4
CREATE TABLE IF NOT EXISTS authorities(
    id              SERIAL PRIMARY KEY,
    username        VARCHAR(50) REFERENCES users(username),
    authority       VARCHAR(50)
);