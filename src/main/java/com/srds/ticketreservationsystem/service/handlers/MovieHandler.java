package com.srds.ticketreservationsystem.service.handlers;

import lombok.Setter;

public class MovieHandler extends BaseUIHandler {
    @Setter
    private String theater;
    private static MovieHandler instance;

    @Override
    public void handle() {
        if (theater != null) {
            System.out.println("Wybrane kino: " + theater);
        }
        System.out.println("Filmy: ..."); // TODO: choose movies
        setNext(SeatHandler.newInstance());
        super.handle();
    }

    public static MovieHandler newInstance() {
        if (instance == null) {
            instance = new MovieHandler();
        }
        return instance;
    }
}
