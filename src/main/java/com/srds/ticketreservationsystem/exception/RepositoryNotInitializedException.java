package com.srds.ticketreservationsystem.exception;

public class RepositoryNotInitializedException extends RuntimeException {
    public RepositoryNotInitializedException() {
        super("Repository is not initialized");
    }
}
