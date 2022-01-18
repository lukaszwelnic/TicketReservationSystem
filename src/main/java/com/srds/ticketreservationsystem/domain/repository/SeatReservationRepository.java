package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.SeatReservation;
import com.srds.ticketreservationsystem.exception.RepositoryNotInitializedException;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class SeatReservationRepository extends GenericRepository<SeatReservation> {
    @Setter
    private static SeatReservationRepository instance;

    public SeatReservationRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM seat_reservation;";
        UPSERT = "UPDATE seat_reservation SET row = ?, seat = ? where date = ? and cinema_name = ? and theater_id = ? and client_nick = ? and movie_name = ?;";
        DELETE_ALL = "TRUNCATE seat_reservation;";
    }

    @Override
    public void upsert(SeatReservation seatReservation) {
        upsert(seatReservation.getRow(), seatReservation.getSeat(),
                seatReservation.getDate(), seatReservation.getCinemaName(),
                seatReservation.getTheaterId(), seatReservation.getClientNick(),
                seatReservation.getMovieName());
    }

    public List<SeatReservation> select(Date date) {
        SELECT = "SELECT * FROM seat_reservation WHERE date = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(date);
        return executeSelect(boundStatement);
    }

    public List<SeatReservation> select(Date date, String cinemaName) {
        SELECT = "SELECT * FROM seat_reservation WHERE date = ? AND cinema_name = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(date, cinemaName);
        return executeSelect(boundStatement);
    }

    public List<SeatReservation> select(Date date, String cinemaName, Integer theaterId) {
        SELECT = "SELECT * FROM seat_reservation WHERE date = ? AND cinema_name = ? AND theater_id = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(date, cinemaName, theaterId);
        return executeSelect(boundStatement);
    }

    @Override
    protected SeatReservation decodeModel(Row row) {
        return new SeatReservation(row);
    }

    public static SeatReservationRepository getInstance() {
        if (instance == null) {
            throw new RepositoryNotInitializedException();
        }
        return instance;
    }
}
