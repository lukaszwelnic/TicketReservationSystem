package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Cinema;
import com.srds.ticketreservationsystem.domain.model.ClientReservation;
import com.srds.ticketreservationsystem.exception.RepositoryNotInitializedException;
import lombok.Setter;

public class ClientReservationRepository extends GenericRepository<ClientReservation> {
    @Setter
    private static ClientReservationRepository instance;

    public ClientReservationRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM client_reservation;";
        UPSERT = "UPDATE client_reservation SET reservation_date = ?, row = ?, seat = ?, cinema_name = ?, theater_id = ?, price = ?, created_at = ? where client_nick = ? and movie_name = ?;";
        DELETE = "";
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

    @Override
    public void delete(ClientReservation clientReservation) {
//        delete(clientReservation.getId());
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
