package com.srds.ticketreservationsystem.service.handlers;

import lombok.Getter;

public class LogInHandler extends BaseUIHandler {
    private static LogInHandler instance;
    @Getter
    private String login;

    @Override
    public void handle() {
        System.out.print("Podaj login: ");
        login = userInput.nextLine();
        setNext(MainMenuHandler.newInstance());
        super.handle();
    }

    public static LogInHandler newInstance() {
        if (instance == null) {
            instance = new LogInHandler();
        }
        return instance;
    }
}
