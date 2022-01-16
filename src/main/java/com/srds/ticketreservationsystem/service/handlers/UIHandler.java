package com.srds.ticketreservationsystem.service.handlers;

public interface UIHandler {
    void setNext(UIHandler handler);
    void handle();
}
