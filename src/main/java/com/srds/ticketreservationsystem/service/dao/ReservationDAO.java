package com.srds.ticketreservationsystem.service.dao;

import com.srds.ticketreservationsystem.domain.repository.SeatReservationRepository;
import com.srds.ticketreservationsystem.service.model.Seat;

import java.util.Collections;
import java.util.List;

public class ReservationDAO {
    private SeatReservationRepository seatReservationRepository;

    public List<Seat> availableSeats() {
        // TODO
        return Collections.singletonList(new Seat(1, 1));
    }
}
