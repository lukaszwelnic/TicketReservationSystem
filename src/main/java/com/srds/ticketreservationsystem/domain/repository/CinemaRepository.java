package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Cinema;

import java.util.List;

public class CinemaRepository extends GenericRepository<Cinema> {

    public CinemaRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM CINEMA;";
        UPSERT = "UPDATE CINEMA SET number_of_theaters = ? " +
                 "WHERE cinema_name = ?;";
        DELETE_ALL = "TRUNCATE CINEMA;";
    }

    public List<Cinema> executeStatement(String cinemaName) {
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
}
