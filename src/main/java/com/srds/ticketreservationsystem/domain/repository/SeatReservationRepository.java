package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.SeatReservation;

public class SeatReservationRepository extends GenericRepository<SeatReservation> {
    public SeatReservationRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM seat_reservation;";
        UPSERT = "UPDATE seat_reservation SET row = ?, seat = ? where date = ? and cinema_name = ?, theater_id = ?;";
        DELETE_ALL = "TRUNCATE seat_reservation;";
    }

    @Override
    public void upsert(SeatReservation seatReservation) {
        upsert(seatReservation.getRow(), seatReservation.getSeat(),
                seatReservation.getDate(), seatReservation.getCinemaName(),
                seatReservation.getTheaterId());
    }

    @Override
    public void delete(SeatReservation seatReservation) {

    }

    @Override
    protected SeatReservation decodeModel(Row row) {
        return new SeatReservation(row);
    }
}
