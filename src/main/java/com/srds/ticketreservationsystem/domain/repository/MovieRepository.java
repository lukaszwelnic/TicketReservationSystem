package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Movie;

public class MovieRepository extends GenericRepository<Movie> {

    public MovieRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM MOVIE;";
        UPSERT = "UPDATE MOVIE SET cinema_name = ?," +
                "movie_name = ?," +
                "theater_id = ?," +
                "date = ?;";
        DELETE = "DELETE ? FROM MOVIE WHERE cinema_name = ?;";
        DELETE_ALL = "TRUNCATE MOVIE;";
    }

    @Override
    public void upsert(Movie movie) {
        upsert(movie.getCinema_name(), movie.getMovie_name(), movie.getTheater_id(),
                movie.getDate());
    }

    @Override
    public void delete(Movie movie) {
        delete(movie.getCinema_name(), movie.getMovie_name());
    }

    @Override
    protected Movie decodeModel(Row row) {
        return new Movie(row);
    }
}
