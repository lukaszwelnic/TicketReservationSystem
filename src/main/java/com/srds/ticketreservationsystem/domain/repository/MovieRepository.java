package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Movie;
import java.util.List;

public class MovieRepository extends GenericRepository<Movie> {

    public MovieRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM MOVIE;";
        UPSERT = "UPDATE MOVIE SET theater_id = ?," +
                "date = ? " +
                "WHERE cinema_name = ? AND movie_name = ?;";
        DELETE_ALL = "TRUNCATE MOVIE;";
    }

    public List<Movie> select(String cinemaName) {
        SELECT = "SELECT * FROM MOVIE WHERE cinema_name = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(cinemaName);
        return executeSelect(boundStatement);
    }

    public List<Movie> select(String cinemaName, String movieName) {
        SELECT = "SELECT * FROM MOVIE WHERE cinema_name = ? AND movie_name = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(cinemaName, movieName);
        return executeSelect(boundStatement);
    }

    @Override
    public void upsert(Movie movie) {
        upsert(movie.getTheaterId(), movie.getDate(), movie.getCinemaName(), movie.getMovieName());
    }

    @Override
    protected Movie decodeModel(Row row) {
        return new Movie(row);
    }
}
