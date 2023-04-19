-- liquibase formatted sql

-- changeset weare4saken:5
CREATE TABLE IF NOT EXISTS ads(
    id              SERIAL PRIMARY KEY,
    title           VARCHAR (100) NOT NULL,
    description     TEXT,
    price           INTEGER NOT NULL,
    author_id       INT REFERENCES users(id),
    image_id        INTEGER REFERENCES image(id)
--         VARCHAR (255)
);