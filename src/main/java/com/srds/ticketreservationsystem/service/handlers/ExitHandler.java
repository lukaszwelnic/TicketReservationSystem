package com.srds.ticketreservationsystem.service.handlers;

public class ExitHandler extends BaseUIHandler {
    private static ExitHandler instance;

    @Override
    public void handle() {
        System.exit(0);
    }

    public static ExitHandler newInstance() {
        if (instance == null) {
            instance = new ExitHandler();
        }
        return instance;
    }
}
