CREATE TABLE genres (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE movies (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(200) NOT NULL,
                        release_date DATE,
                        duration INT,
                        score DOUBLE PRECISION,
                        genre_id INT REFERENCES genres(id)
);

CREATE TABLE actors (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(200) NOT NULL
);

CREATE TABLE movie_actors (
                              movie_id INT REFERENCES movies(id),
                              actor_id INT REFERENCES actors(id),
                              PRIMARY KEY (movie_id, actor_id)
);