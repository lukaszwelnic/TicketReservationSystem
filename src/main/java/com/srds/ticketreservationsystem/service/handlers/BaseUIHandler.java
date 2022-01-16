package com.srds.ticketreservationsystem.service.handlers;

import java.util.List;
import java.util.Scanner;

public class BaseUIHandler implements UIHandler {
    protected List<String> options;
    protected List<UIHandler> handlers;
    protected final Scanner userInput = new Scanner(System.in);

    private UIHandler handler;

    @Override
    public void setNext(UIHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle() {
        handler.handle();
    }

    protected int getUserResponse() {
        String userResponse = getUserResponseString();
        int integerResponse;
        try {
            integerResponse = Integer.parseInt(userResponse);
            if (integerResponse > options.size() || integerResponse < 1) {
                throw new RuntimeException();
            }
        } catch (Exception exception) {
            return getUserResponse();
        }
        return integerResponse;
    }

    private String getUserResponseString() {
        int i = 0;
        for (String option : options) {
            i++;
            System.out.println(i + ") " + option);
        }
        return userInput.nextLine();
    }
}
