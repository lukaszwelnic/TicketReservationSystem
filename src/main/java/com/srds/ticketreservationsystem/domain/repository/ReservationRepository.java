package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Reservation;

public class ReservationRepository extends GenericRepository<Reservation> {

    public ReservationRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM RESERVATION;";
    }

    @Override
    protected Reservation decodeModel(Row row) {
        return new Reservation(row);
    }
}
