package com.srds.ticketreservationsystem;

import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.repository.*;
import com.srds.ticketreservationsystem.service.UserInterface;

public class Main {
    public static void main(String[] args) {
        try (CassandraConnector cassandraConnector = new CassandraConnector()) {
            CinemaRepository.setInstance(new CinemaRepository(cassandraConnector));
            ClientReservationRepository.setInstance(new ClientReservationRepository(cassandraConnector));
            MovieRepository.setInstance(new MovieRepository(cassandraConnector));
            SeatReservationRepository.setInstance(new SeatReservationRepository(cassandraConnector));
            TheaterRepository.setInstance(new TheaterRepository(cassandraConnector));

            UserInterface.mainLoop();
        }
    }
}