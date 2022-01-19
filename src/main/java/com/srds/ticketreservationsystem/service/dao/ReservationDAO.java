package com.srds.ticketreservationsystem.service.dao;

import com.srds.ticketreservationsystem.domain.model.ClientReservation;
import com.srds.ticketreservationsystem.domain.model.Movie;
import com.srds.ticketreservationsystem.domain.model.SeatReservation;
import com.srds.ticketreservationsystem.domain.model.Theater;
import com.srds.ticketreservationsystem.domain.repository.ClientReservationRepository;
import com.srds.ticketreservationsystem.domain.repository.SeatReservationRepository;
import com.srds.ticketreservationsystem.domain.repository.TheaterRepository;
import com.srds.ticketreservationsystem.service.handlers.LogInHandler;
import com.srds.ticketreservationsystem.service.model.Seat;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ReservationDAO {

    public List<Seat> availableSeats(Movie movie) {
        List<Theater> theaters = TheaterRepository.getInstance().select(movie.getCinemaName(), movie.getTheaterId());
        if (theaters.size() <= 0) {
            return Collections.emptyList();
        }
        Theater theater = theaters.get(0);
        List<Seat> seats = new ArrayList<>();
        List<SeatReservation> seatReservationList = SeatReservationRepository.getInstance().select(movie.getDate(), movie.getCinemaName(), movie.getTheaterId());
        for (int i = 0; i < theater.getNumberOfRows(); i++) {
            for (int j = 0; j < theater.getNumberOfSeats(); j++) {
                if (isSeatTaken(seatReservationList, i, j)) {
                    continue;
                }
                seats.add(new Seat(i, j));
            }
        }
        return seats;
    }

    public synchronized void reserveSeat(Movie movie, Seat seat, String login) {
        SeatReservationRepository.getInstance()
                .upsert(new SeatReservation(
                        movie.getDate(), movie.getCinemaName(),
                        movie.getTheaterId(), login,
                        movie.getMovieName(), seat.getRow(), seat.getSeat()));
        ClientReservationRepository.getInstance()
                .upsert(new ClientReservation(login, movie.getMovieName(),
                        movie.getDate(), seat.getRow(), seat.getSeat(),
                        movie.getCinemaName(), movie.getTheaterId(),
                        1.0f, Date.from(Instant.now())));
    }

    private boolean isSeatTaken(List<SeatReservation> seatReservationList, Integer row, Integer seat) {
        for (SeatReservation reservation : seatReservationList) {
            if (reservation.getRow().equals(row) && reservation.getSeat().equals(seat)) {
                return true;
            }
        }
        return false;
    }
}
