CREATE TABLE IF NOT EXISTS Users (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        name varchar(300) NOT NULL,
        email varchar NOT NULL,
        country varchar NOT NULL,
        CONSTRAINT UQ_USERS_EMAIL UNIQUE (email)
);
CREATE TABLE IF NOT EXISTS Directors (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        name varchar(300) NOT NULL
);
CREATE TABLE IF NOT EXISTS Categories (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        name varchar(300) NOT NULL
);
CREATE TABLE IF NOT EXISTS Films (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        title varchar(1000) NOT NULL,
        description varchar(3000),
        release_year int NOT NULL,
        director_id BIGINT NOT NULL,
        country varchar NOT NULL,
        rating varchar NOT NULL,
        duration timestamp NOT NULL,
        category_id BIGINT NOT NULL,
        CONSTRAINT FK_FILMS_ON_DIRECTOR FOREIGN KEY (director_id) REFERENCES Directors (id),
        CONSTRAINT FK_FILMS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES Categories (id)
);