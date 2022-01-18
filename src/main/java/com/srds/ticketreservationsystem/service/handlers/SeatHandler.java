package com.srds.ticketreservationsystem.service.handlers;

import com.srds.ticketreservationsystem.domain.model.Movie;
import com.srds.ticketreservationsystem.service.dao.ReservationDAO;
import com.srds.ticketreservationsystem.service.model.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class SeatHandler extends BaseUIHandler {
    private static SeatHandler instance;

    @Override
    public void handle() {
        ReservationDAO reservationDAO = new ReservationDAO();
        Movie movie = MovieHandler.newInstance().getMovie();
        List<Seat> seats = reservationDAO.availableSeats(movie);
        int seatIndex = chooseSeat(seats);
        if (seatIndex > seats.size()) {
            setNext(ExitHandler.newInstance());
        } else {
            reservationDAO.reserveSeat(movie, seats.get(seatIndex - 1));
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
