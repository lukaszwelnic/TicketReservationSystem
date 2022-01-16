package com.srds.ticketreservationsystem.service;

import com.srds.ticketreservationsystem.service.handlers.LogInHandler;
import com.srds.ticketreservationsystem.service.handlers.MainMenuHandler;
import com.srds.ticketreservationsystem.service.handlers.UIHandler;

public class UserInterface {
    public static void mainLoop() {
        UIHandler handler = LogInHandler.newInstance();
        handler.handle();
    }
}
