package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Cinema;
import com.srds.ticketreservationsystem.domain.model.ClientReservation;
import com.srds.ticketreservationsystem.exception.RepositoryNotInitializedException;
import lombok.Setter;

import java.util.List;

public class ClientReservationRepository extends GenericRepository<ClientReservation> {
    @Setter
    private static ClientReservationRepository instance;

    public ClientReservationRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM client_reservation;";
        UPSERT = "UPDATE client_reservation SET reservation_date = ?, row = ?, seat = ?, cinema_name = ?, theater_id = ?, price = ?, created_at = ? where client_nick = ? and movie_name = ?;";
        DELETE_ALL = "TRUNCATE client_reservation;";
    }

    @Override
    public void upsert(ClientReservation clientReservation) {
        upsert(clientReservation.getReservationDate(), clientReservation.getRow(),
                clientReservation.getSeat(), clientReservation.getCinemaName(),
                clientReservation.getTheaterId(), clientReservation.getPrice(),
                clientReservation.getCreatedAt(), clientReservation.getClientNick(),
                clientReservation.getMovieName());
    }

    public List<ClientReservation> select(String clientNick) {
        SELECT = "SELECT * FROM client_reservation WHERE client_nick = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(clientNick);
        return executeSelect(boundStatement);
    }

    public List<ClientReservation> select(String clientNick, String movieName) {
        SELECT = "SELECT * FROM client_reservation WHERE client_nick = ? AND movie_name = ?;";
        BoundStatement boundStatement = new BoundStatement(session.prepare(SELECT));
        boundStatement.bind(clientNick, movieName);
        return executeSelect(boundStatement);
    }

    @Override
    protected ClientReservation decodeModel(Row row) {
        return new ClientReservation(row);
    }

    public static ClientReservationRepository getInstance() {
        if (instance == null) {
            throw new RepositoryNotInitializedException();
        }
        return instance;
    }
}
