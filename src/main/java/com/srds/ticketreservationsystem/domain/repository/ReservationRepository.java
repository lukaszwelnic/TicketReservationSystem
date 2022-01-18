package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Reservation;

public class ReservationRepository extends GenericRepository<Reservation> {

    public ReservationRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM RESERVATION;";
        UPSERT = "UPDATE RESERVATION SET client_nick = ?," +
                "movie_name = ?," +
                "reservation_date = ?," +
                "row = ?," +
                "seat = ?," +
                "cinema_name = ?," +
                "theater_id = ?," +
                "price = ?" +
                "-created_at = ?;";
        DELETE = "DELETE ? FROM RESERVATION WHERE client_nick = ?;";
        DELETE_ALL = "TRUNCATE RESERVATION;";
    }

    @Override
    public void upsert(Reservation reservation) {
        upsert(reservation.getClient_nick(), reservation.getMovie_name(), reservation.getReservation_date(),
                reservation.getRow(), reservation.getSeat(), reservation.getCinema_name(), reservation.getTheater_id(),
                reservation.getPrice(), reservation.getCreated_at());
    }

    @Override
    public void delete(Reservation reservation) {
        delete(reservation.getMovie_name(), reservation.getClient_nick());
    }

    @Override
    protected Reservation decodeModel(Row row) {
        return new Reservation(row);
    }
}
