package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Theater;

import java.util.List;

public class TheaterRepository extends GenericRepository<Theater> {

    public TheaterRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM THEATER;";
        UPSERT = "UPDATE THEATER SET number_of_rows = ?," +
                "number_of_seats = ? " +
                "WHERE cinema_name = ? theater_id = ?;";
        DELETE_ALL = "TRUNCATE THEATER;";
    }

    public List<Theater> select(String cinemaName) {
        SELECT = "SELECT * FROM THEATER WHERE cinema_name = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(cinemaName);
        return executeSelect(boundStatement);
    }

    public List<Theater> select(String cinemaName, Integer theaterId) {
        SELECT = "SELECT * FROM THEATER WHERE cinema_name = ? AND theater_id = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(cinemaName, theaterId);
        return executeSelect(boundStatement);
    }

    @Override
    public void upsert(Theater theater) {
        upsert(theater.getNumberOfRows(), theater.getNumberOfSeats(),
                theater.getCinemaName(), theater.getTheaterId());
    }

    @Override
    protected Theater decodeModel(Row row) {
        return new Theater(row);
    }
}