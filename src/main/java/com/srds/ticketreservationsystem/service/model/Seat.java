package com.srds.ticketreservationsystem.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Seat {
    private final Integer row;
    private final Integer seat;
}
