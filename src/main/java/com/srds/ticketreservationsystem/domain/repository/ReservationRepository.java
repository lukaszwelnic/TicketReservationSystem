package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Reservation;

public class ReservationRepository extends GenericRepository<Reservation> {

    public ReservationRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM RESERVATION;";
        UPSERT = "UPDATE RESERVATION SET ";
        DELETE = "";
        DELETE_ALL = "TRUNCATE RESERVATION;";
    }

    @Override
    public void upsert(Reservation reservation) {
        upsert(reservation.getClient_nick());
    }

    @Override
    public void delete(Reservation reservation) {
        delete(reservation.getClient_nick());
    }

    @Override
    protected Reservation decodeModel(Row row) {
        return new Reservation(row);
    }
}
