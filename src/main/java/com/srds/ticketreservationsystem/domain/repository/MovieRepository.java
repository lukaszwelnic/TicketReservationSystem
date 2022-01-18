package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Movie;

public class MovieRepository extends GenericRepository<Movie> {
    public MovieRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM movie;";
        UPSERT = "UPDATE movie SET theater_id = ?, date = ? where cinema_name = ? and movie_name = ?;";
        DELETE_ALL = "TRUNCATE movie;";
    }

    @Override
    public void upsert(Movie movie) {
        upsert(movie.getTheaterId(), movie.getDate(),
                movie.getCinemaName(), movie.getMovieName());
    }

    @Override
    public void delete(Movie movie) {

    }

    @Override
    protected Movie decodeModel(Row row) {
        return new Movie(row);
    }
}
