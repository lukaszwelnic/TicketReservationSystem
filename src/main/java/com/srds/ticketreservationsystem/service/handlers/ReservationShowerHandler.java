package com.srds.ticketreservationsystem.service.handlers;

public class ReservationShowerHandler extends BaseUIHandler {
    private static ReservationShowerHandler instance;

    @Override
    public void handle() {
        System.out.println("Rezerwacje: ..."); // TODO
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
