package com.srds.ticketreservationsystem.service.handlers;

public class SeatHandler extends BaseUIHandler {
    private static SeatHandler instance;

    @Override
    public void handle() {
        System.out.println("Wybierz miejsce:");
        setNext(MainMenuHandler.newInstance());
        super.handle();
    }

    public static SeatHandler newInstance() {
        if (instance == null) {
            instance = new SeatHandler();
        }
        return instance;
    }
}
