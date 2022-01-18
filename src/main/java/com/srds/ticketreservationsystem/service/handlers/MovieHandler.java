package com.srds.ticketreservationsystem.service.handlers;

import com.srds.ticketreservationsystem.domain.model.Cinema;
import com.srds.ticketreservationsystem.domain.model.Movie;
import com.srds.ticketreservationsystem.domain.repository.MovieRepository;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class MovieHandler extends BaseUIHandler {
    private static MovieHandler instance;
    @Getter
    private Movie movie;

    @Override
    public void handle() {
        Cinema cinema = SelectCinemaHandler.newInstance().getCinema();
        System.out.println("Wybrane kino: " + cinema.getCinemaName());
        List<Movie> movies = MovieRepository.getInstance().select(cinema.getCinemaName());
        int movieIndex = chooseMovie(movies);
        if (movieIndex > movies.size()) {
            setNext(ExitHandler.newInstance());
        } else {
            movie = movies.get(movieIndex - 1);
            setNext(SeatHandler.newInstance());
        }
        super.handle();
    }

    private int chooseMovie(List<Movie> movies) {
        options = movies.stream()
                .map(this::stringifyMovie)
                .collect(Collectors.toList());
        options.add("Wyjdź");
        System.out.println("Filmy:");
        return getUserResponse();
    }

    private String stringifyMovie(Movie movie) {
        return movie.getMovieName() + " | " + movie.getDate();
    }

    public static MovieHandler newInstance() {
        if (instance == null) {
            instance = new MovieHandler();
        }
        return instance;
    }
}
