package com.srds.ticketreservationsystem.service.handlers;

import com.srds.ticketreservationsystem.domain.model.ClientReservation;
import com.srds.ticketreservationsystem.domain.repository.ClientReservationRepository;

public class ReservationShowerHandler extends BaseUIHandler {
    private static ReservationShowerHandler instance;

    @Override
    public void handle() {
        System.out.println("Rezerwacje:");
        for (ClientReservation reservation : ClientReservationRepository.getInstance().select(LogInHandler.newInstance().getLogin())) {
            System.out.println(reservation.getCinemaName() + " | " + reservation.getMovieName() + " | " + reservation.getReservationDate());
        }
        setNext(MainMenuHandler.newInstance());
        super.handle();
    }

    public static ReservationShowerHandler newInstance() {
        if (instance == null) {
            instance = new ReservationShowerHandler();
        }
        return instance;
    }
}
