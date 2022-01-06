package com.srds.ticketreservationsystem;

import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Reservation;
import com.srds.ticketreservationsystem.domain.repository.ReservationRepository;

public class Main {
    public static void main(String[] args) {
        try (CassandraConnector connector = new CassandraConnector()) {
            ReservationRepository reservationRepository = new ReservationRepository(connector);
            for (Reservation reservation : reservationRepository.fetchAll()) {
                System.out.println(reservation.getName());
            }
        }
    }
}
