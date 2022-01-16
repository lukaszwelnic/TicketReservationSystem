package com.srds.ticketreservationsystem.service.handlers;

public class SelectTheaterHandler extends BaseUIHandler {
    private static SelectTheaterHandler instance;

    @Override
    public void handle() {
        System.out.println("Kina: ..."); // TODO: Show theaters and ask user to choose
        MovieHandler handler = MovieHandler.newInstance();
        handler.setTheater("Testowe kino"); // TODO: Set chosen theater
        setNext(handler);
        super.handle();
    }

    public static SelectTheaterHandler newInstance() {
        if (instance == null) {
            instance = new SelectTheaterHandler();
        }
        return instance;
    }
}
