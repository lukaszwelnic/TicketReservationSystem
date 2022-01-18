package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Reservation;

import java.util.List;

public class ReservationRepository extends GenericRepository<Reservation> {

    public ReservationRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM RESERVATION;";
        UPSERT = "UPDATE RESERVATION SET reservation_date = ?," +
                "row = ?," +
                "seat = ?," +
                "cinema_name = ?," +
                "theater_id = ?," +
                "price = ?" +
                "created_at = ? " +
                "WHERE client_nick = ? AND movie_name = ?;";
        DELETE_ALL = "TRUNCATE RESERVATION;";
    }

    public List<Reservation> select(String clientNick) {
        SELECT = "SELECT * FROM RESERVATION WHERE client_nick = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(clientNick);
        return executeSelect(boundStatement);
    }

    public List<Reservation> select(String clientNick, String movieName) {
        SELECT = "SELECT * FROM RESERVATION WHERE client_nick = ? AND movie_name = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(clientNick, movieName);
        return executeSelect(boundStatement);
    }

    @Override
    public void upsert(Reservation reservation) {
        upsert(reservation.getReservationDate(), reservation.getRow(), reservation.getSeat(),
                reservation.getCinemaName(), reservation.getTheaterId(), reservation.getPrice(),
                reservation.getCreatedAt(), reservation.getClientNick(), reservation.getMovieName());
    }

    @Override
    protected Reservation decodeModel(Row row) {
        return new Reservation(row);
    }
}
