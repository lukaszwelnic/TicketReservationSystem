package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Cinema;

public class CinemaRepository extends GenericRepository<Cinema> {

    public CinemaRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM CINEMA;";
        UPSERT = "UPDATE CINEMA SET cinema_name = ?," +
                "number_of_theaters = ?;";
        DELETE = "DELETE ? FROM CINEMA WHERE cinema_name = ?;";
        DELETE_ALL = "TRUNCATE CINEMA;";
    }

    @Override
    public void upsert(Cinema cinema) {
        upsert(cinema.getCinema_name(), cinema.getNumber_of_theaters());
    }

    @Override
    public void delete(Cinema cinema) {
        delete(cinema.getCinema_name(), cinema.getNumber_of_theaters());
    }

    @Override
    protected Cinema decodeModel(Row row) {
        return new Cinema(row);
    }
}
