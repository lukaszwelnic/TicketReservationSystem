package com.srds.ticketreservationsystem.exception;

public class CannotExecuteStatementException extends RuntimeException {
    public CannotExecuteStatementException(String statement, Exception exception) {
        super("Cannot execute statement: " + statement, exception);
    }
}
