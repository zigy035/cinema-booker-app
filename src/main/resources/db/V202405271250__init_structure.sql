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
    seat_rows INTEGER NOT NULL,
    seat_columns INTEGER NOT NULL
);

CREATE TABLE auth_user (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    access_role VARCHAR(10) NOT NULL
);

CREATE TABLE showtime (
    id BIGSERIAL PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theatre_id BIGINT NOT NULL,
    show_time TIMESTAMP NOT NULL
);

ALTER TABLE showtime
ADD CONSTRAINT showtime_movie_id_fk
FOREIGN KEY(movie_id) REFERENCES movie(id);

ALTER TABLE showtime
ADD CONSTRAINT showtime_theatre_id_fk
FOREIGN KEY(theatre_id) REFERENCES theatre(id);

ALTER TABLE showtime
ADD CONSTRAINT showtime_movie_theatre_time_uq
UNIQUE (movie_id, theatre_id, show_time);

CREATE TABLE ticket (
    id BIGSERIAL PRIMARY KEY,
    showtime_id BIGINT NOT NULL,
    auth_user_id BIGINT NOT NULL,
    seat_row VARCHAR(1) NOT NULL,
    seat_column VARCHAR(2) NOT NULL
);

ALTER TABLE ticket
ADD CONSTRAINT ticket_showtime_id_fk
FOREIGN KEY(showtime_id) REFERENCES showtime(id);

ALTER TABLE ticket
ADD CONSTRAINT ticket_auth_user_id_fk
FOREIGN KEY(auth_user_id) REFERENCES auth_user(id);

ALTER TABLE ticket
ADD CONSTRAINT ticket_showtime_id_row_col_uq
UNIQUE (showtime_id, seat_row, seat_column);