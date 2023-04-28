-- liquibase formatted sql

-- changeset weare4saken:3
CREATE TABLE IF NOT EXISTS users (
    id              SERIAL PRIMARY KEY,
    username        VARCHAR(50) UNIQUE NOT NULL,
    first_name      VARCHAR(25) NOT NULL,
    last_name       VARCHAR(25) NOT NULL,
    phone           VARCHAR(15) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    enabled         BOOLEAN,
    role            VARCHAR(10),
    avatar_id       INTEGER REFERENCES avatar(id)
);