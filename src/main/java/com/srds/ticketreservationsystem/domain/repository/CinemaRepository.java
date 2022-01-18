package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Cinema;

public class CinemaRepository extends GenericRepository<Cinema> {
    public CinemaRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM cinema;";
        UPSERT = "UPDATE cinema SET number_of_theaters = ? where cinema_name = ?;";
        DELETE_ALL = "TRUNCATE cinema;";
    }

    @Override
    public void upsert(Cinema cinema) {
        upsert(cinema.getNumberOfTheaters(), cinema.getCinemaName());
    }

    @Override
    public void delete(Cinema cinema) {

    }

    @Override
    protected Cinema decodeModel(Row row) {
        return new Cinema(row);
    }
}
