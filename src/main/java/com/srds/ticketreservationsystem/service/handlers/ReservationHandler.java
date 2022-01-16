package com.srds.ticketreservationsystem.service.handlers;

import lombok.Setter;

import java.util.Arrays;

public class ReservationHandler extends BaseUIHandler {

    private static ReservationHandler instance;

    private ReservationHandler() {
        options = Arrays.asList("Wybierz kino", "Wyjdź");
        handlers = Arrays.asList(SelectTheaterHandler.newInstance(), ExitHandler.newInstance());
    }

    @Override
    public void handle() {
        int userResponse = getUserResponse();
        setNext(handlers.get(userResponse - 1));
        super.handle();
    }

    public static ReservationHandler newInstance() {
        if (instance == null) {
            instance = new ReservationHandler();
        }
        return instance;
    }

}
