package com.srds.ticketreservationsystem.service.handlers;

import java.util.Arrays;

public class MainMenuHandler extends BaseUIHandler {
    private static MainMenuHandler instance;

    private MainMenuHandler() {
        options = Arrays.asList("Zarezerwuj film", "Pokaż moje rezerwacje", "Wyjdź");
        handlers = Arrays.asList(SelectCinemaHandler.newInstance(), ReservationShowerHandler.newInstance(), ExitHandler.newInstance());
    }

    @Override
    public void handle() {
        System.out.println("Login: " + LogInHandler.newInstance().getLogin());
        int userResponse = getUserResponse();
        setNext(handlers.get(userResponse - 1));
        super.handle();
    }

    public static MainMenuHandler newInstance() {
        if (instance == null) {
            instance = new MainMenuHandler();
        }
        return instance;
    }

}
