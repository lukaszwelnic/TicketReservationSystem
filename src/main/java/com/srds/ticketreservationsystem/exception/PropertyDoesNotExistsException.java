package com.srds.ticketreservationsystem.exception;

public class PropertyDoesNotExistsException extends RuntimeException {

    public PropertyDoesNotExistsException(Exception exception) {
        super("Properties file doesn't exist", exception);
    }

}
