package com.srds.ticketreservationsystem.service.handlers;

import com.srds.ticketreservationsystem.domain.model.Cinema;
import com.srds.ticketreservationsystem.domain.model.Movie;
import com.srds.ticketreservationsystem.domain.repository.MovieRepository;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class MovieHandler extends BaseUIHandler {
    private static MovieHandler instance;

    @Override
    public void handle() {
        Cinema cinema = SelectCinemaHandler.newInstance().getCinema();
        System.out.println("Wybrane kino: " + cinema.getCinemaName());
        List<Movie> movies = MovieRepository.getInstance().fetchAll(); // TODO: nie fetch all tylko wszystkie filmy dla danego kina
        int movieIndex = chooseMovie(movies);
        if (movieIndex > movies.size()) {
            setNext(ExitHandler.newInstance());
        } else {
            System.out.println(movies.get(movieIndex - 1));
            // TODO: rezerwacja miejsca
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
