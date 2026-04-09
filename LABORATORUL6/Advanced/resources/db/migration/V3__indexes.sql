CREATE INDEX idx_movie_list_created_at ON movie_list(created_at);
CREATE INDEX idx_movie_list_movies_list_id ON movie_list_movies(list_id);
CREATE INDEX idx_movie_list_movies_movie_id ON movie_list_movies(movie_id);