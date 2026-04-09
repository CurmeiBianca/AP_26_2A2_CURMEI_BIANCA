CREATE TABLE movie_list (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE movie_list_movies (
    list_id INT NOT NULL,
    movie_id INT NOT NULL,
    PRIMARY KEY (list_id, movie_id),
    FOREIGN KEY (list_id) REFERENCES movie_list(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);