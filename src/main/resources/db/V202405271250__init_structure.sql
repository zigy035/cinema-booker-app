CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    genre VARCHAR(20) NOT NULL,
    duration INTEGER NOT NULL
);

CREATE TABLE theatre (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    row_count INTEGER NOT NULL,
    col_count INTEGER NOT NULL
);

CREATE TABLE auth_user (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    access INT8 NOT NULL
);

CREATE TABLE broadcast (
    id BIGSERIAL PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theatre_id BIGINT NOT NULL,
    broadcast_time TIMESTAMP NOT NULL
);

ALTER TABLE broadcast
ADD CONSTRAINT broadcast_movie_id_fk
FOREIGN KEY(movie_id) REFERENCES movie(id);

ALTER TABLE broadcast
ADD CONSTRAINT broadcast_theatre_id_fk
FOREIGN KEY(theatre_id) REFERENCES theatre(id);

ALTER TABLE broadcast
ADD CONSTRAINT broadcast_movie_theatre_time_uq
UNIQUE (movie_id, theatre_id, broadcast_time);

CREATE TABLE ticket (
    id BIGSERIAL PRIMARY KEY,
    broadcast_id BIGINT NOT NULL,
    auth_user_id BIGINT NOT NULL,
    seat_row VARCHAR(3) NOT NULL,
    seat_column VARCHAR(3) NOT NULL
);

ALTER TABLE ticket
ADD CONSTRAINT ticket_broadcast_id_fk
FOREIGN KEY(broadcast_id) REFERENCES broadcast(id);

ALTER TABLE ticket
ADD CONSTRAINT ticket_auth_user_id_fk
FOREIGN KEY(auth_user_id) REFERENCES auth_user(id);

ALTER TABLE ticket
ADD CONSTRAINT ticket_broadcast_id_row_col_uq
UNIQUE (broadcast_id, seat_row, seat_column);