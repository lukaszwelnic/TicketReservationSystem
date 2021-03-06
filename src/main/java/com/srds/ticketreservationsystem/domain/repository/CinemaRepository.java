package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Cinema;
import com.srds.ticketreservationsystem.exception.RepositoryNotInitializedException;
import lombok.Setter;

import java.util.List;

public class CinemaRepository extends GenericRepository<Cinema> {
    @Setter
    private static CinemaRepository instance;

    public CinemaRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM CINEMA;";
        UPSERT = "UPDATE CINEMA SET number_of_theaters = ? " +
                 "WHERE cinema_name = ?;";
        DELETE_ALL = "TRUNCATE CINEMA;";
    }

    public List<Cinema> select(String cinemaName) {
        SELECT = "SELECT * FROM CINEMA WHERE cinema_name = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(cinemaName);
        return executeSelect(boundStatement);
    }

    @Override
    public void upsert(Cinema cinema) {
        upsert(cinema.getNumberOfTheaters(), cinema.getCinemaName());
    }

    @Override
    protected Cinema decodeModel(Row row) {
        return new Cinema(row);
    }

    public static CinemaRepository getInstance() {
        if (instance == null) {
            throw new RepositoryNotInitializedException();
        }
        return instance;
    }
}
