package com.srds.ticketreservationsystem.service.handlers;

import com.srds.ticketreservationsystem.service.dao.ReservationDAO;
import com.srds.ticketreservationsystem.service.model.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class SeatHandler extends BaseUIHandler {
    private static SeatHandler instance;

    @Override
    public synchronized void handle() {
        List<Seat> seats = new ReservationDAO().availableSeats();
        int seatIndex = chooseSeat(seats);
        if (seatIndex > seats.size()) {
            setNext(ExitHandler.newInstance());
        } else {
            // TODO: zarezerwuj miejsce
            System.out.println("Siedzenie: " + seats.get(seatIndex - 1).getRow() + "|" + seats.get(seatIndex - 1));
        }
        setNext(MainMenuHandler.newInstance());
        super.handle();
    }

    private int chooseSeat(List<Seat> seats) {
        options = seats
                .stream()
                .map(seat -> seat.getRow() + "|" + seat.getSeat())
                .collect(Collectors.toList());
        options.add("Wyjdź");
        System.out.println("Wybierz miejsce:");
        return getUserResponse();
    }

    public static SeatHandler newInstance() {
        if (instance == null) {
            instance = new SeatHandler();
        }
        return instance;
    }
}
