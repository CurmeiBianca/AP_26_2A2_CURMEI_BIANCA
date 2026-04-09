package org.example.advanced.algorithm;

import org.example.homework.dao.MovieDAO;
import org.example.homework.dao.MovieActorDAO;
import org.example.homework.model.Movie;

import java.util.*;

public class MoviePartitioner {

    private final MovieDAO movieDAO;
    private final MovieActorDAO movieActorDAO;

    public MoviePartitioner(MovieDAO movieDAO, MovieActorDAO movieActorDAO) {
        this.movieDAO = movieDAO;
        this.movieActorDAO = movieActorDAO;
    }

    /**
     * Metoda principala care imparte filmele in liste neinrudite
     */
    public List<List<Movie>> partitionMovies() {

        List<Movie> allMovies = getAllMovies();

        // Mapam ID-ul filmului la obiectul Movie
        Map<Integer, Movie> movieById = new HashMap<>();
        for (Movie movie : allMovies) {
            movieById.put(movie.getId(), movie);
        }

        Map<Integer, List<Integer>> graph = buildGraph(allMovies);

        List<List<Movie>> partitions = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (Movie movie : allMovies) {
            int movieId = movie.getId();

            if (!visited.contains(movieId)) {
                List<Integer> component = new ArrayList<>();
                dfs(movieId, graph, visited, component);

                // Convertim ID-urile in obiecte Movie
                List<Movie> movieGroup = new ArrayList<>();
                for (int id : component) {
                    movieGroup.add(movieById.get(id));
                }

                partitions.add(movieGroup);
            }
        }

        return partitions;
    }

    /**
     * Construim graful filmelor
     */
    private Map<Integer, List<Integer>> buildGraph(List<Movie> movies) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Initializam lista de adiacenta
        for (Movie movie : movies) {
            graph.put(movie.getId(), new ArrayList<>());
        }

        // Pentru fiecare film, conectam filmele care impart actori
        for (Movie movie : movies) {
            int movieId = movie.getId();

            List<Integer> actors = movieActorDAO.findActorsByMovie(movieId);

            for (int actorId : actors) {
                List<Integer> relatedMovies = movieActorDAO.findMoviesByActor(actorId);

                for (int otherMovieId : relatedMovies) {
                    if (otherMovieId != movieId) {
                        graph.get(movieId).add(otherMovieId);
                        graph.get(otherMovieId).add(movieId);
                    }
                }
            }
        }

        return graph;
    }

    /**
     * DFS pentru a gasi componentele conexe
     */
    private void dfs(int movieId,
                     Map<Integer, List<Integer>> graph,
                     Set<Integer> visited,
                     List<Integer> component) {

        visited.add(movieId);
        component.add(movieId);

        for (int neighbor : graph.get(movieId)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, component);
            }
        }
    }

    /**
     * Obtinem toate filmele din baza de date
     */
    private List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        int id = 1;
        while (true) {
            Movie movie = movieDAO.findById(id);
            if (movie == null)
                break;
            movies.add(movie);
            id++;
        }

        return movies;
    }
}
