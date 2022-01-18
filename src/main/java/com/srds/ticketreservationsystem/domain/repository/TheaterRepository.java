package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Theater;

public class TheaterRepository extends GenericRepository<Theater> {

    public TheaterRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM THEATER;";
        UPSERT = "UPDATE THEATER SET cinema_name = ?," +
                "theater_id = ?," +
                "number_of_rows = ?," +
                "number_of_seats = ?;";
        DELETE = "DELETE ? FROM THEATER WHERE cinema_name = ?;";
        DELETE_ALL = "TRUNCATE THEATER;";
    }

    @Override
    public void upsert(Theater theater) {
        upsert(theater.getCinema_name(), theater.getTheater_id(), theater.getNumber_of_rows(),
                theater.getNumber_of_seats());
    }

    @Override
    public void delete(Theater theater) {
        delete(theater.getCinema_name(), theater.getTheater_id());
    }

    @Override
    protected Theater decodeModel(Row row) {
        return new Theater(row);
    }
}