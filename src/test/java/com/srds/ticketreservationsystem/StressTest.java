package com.srds.ticketreservationsystem;

import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.ClientReservation;
import com.srds.ticketreservationsystem.domain.model.Movie;
import com.srds.ticketreservationsystem.domain.model.SeatReservation;
import com.srds.ticketreservationsystem.domain.model.Theater;
import com.srds.ticketreservationsystem.domain.repository.*;
import com.srds.ticketreservationsystem.service.dao.ReservationDAO;
import com.srds.ticketreservationsystem.service.model.Seat;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;

class StressTest {
    private final List<Thread> threads = new ArrayList<>();
    private final int THREADS_NUM = 100;

    private static final CassandraConnector cassandraConnector;

    static {
        cassandraConnector = new CassandraConnector();
        CinemaRepository.setInstance(new CinemaRepository(cassandraConnector));
        ClientReservationRepository.setInstance(new ClientReservationRepository(cassandraConnector));
        MovieRepository.setInstance(new MovieRepository(cassandraConnector));
        SeatReservationRepository.setInstance(new SeatReservationRepository(cassandraConnector));
        TheaterRepository.setInstance(new TheaterRepository(cassandraConnector));
    }

    @BeforeAll
    static void beforeAll() {
        ClientReservationRepository.getInstance().deleteAll();
        SeatReservationRepository.getInstance().deleteAll();
    }

    @AfterAll
    static void afterAll() {
        cassandraConnector.close();
    }

    @Test
    public void performanceTest() throws InterruptedException {
        for (int i = 0; i < THREADS_NUM; i++) {
            Thread thread = new Thread(new RunnableThread("Testowy" + i));
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("False reservations: " + falseReservations() + "/" + THREADS_NUM);
    }

    private static class RunnableThread implements Runnable {
        private final String nick;

        public RunnableThread(String nick) {
            this.nick = nick;
        }

        @SneakyThrows
        @Override
        public void run() {
            try (CassandraConnector cassandraConnector = new CassandraConnector()) {
                ClientReservationRepository clientReservationRepository = new ClientReservationRepository(cassandraConnector);
                MovieRepository movieRepository = new MovieRepository(cassandraConnector);
                SeatReservationRepository seatReservationRepository = new SeatReservationRepository(cassandraConnector);
                TheaterRepository theaterRepository = new TheaterRepository(cassandraConnector);

                Random random = new Random();
                List<Movie> movies;
                do {
                    movies = movieRepository.fetchAll();
                } while (movies.isEmpty());
                Movie movie = movies.get(random.nextInt(movies.size()));
                List<Seat> seats;
                do {
                    seats = availableSeats(theaterRepository, seatReservationRepository, movie);
                } while (seats.isEmpty());
                Seat seat = seats.get(random.nextInt(seats.size()));
                reserveSeat(seatReservationRepository, clientReservationRepository, movie, seat, nick);
            }
        }

        private void reserveSeat(SeatReservationRepository seatReservationRepository, ClientReservationRepository clientReservationRepository, Movie movie, Seat seat, String login) {
            seatReservationRepository
                    .upsert(new SeatReservation(
                            movie.getDate(), movie.getCinemaName(),
                            movie.getTheaterId(), login,
                            movie.getMovieName(), seat.getRow(), seat.getSeat()));
            clientReservationRepository
                    .upsert(new ClientReservation(login, movie.getMovieName(),
                            movie.getDate(), seat.getRow(), seat.getSeat(),
                            movie.getCinemaName(), movie.getTheaterId(),
                            1.0f, Date.from(Instant.now())));
        }

        private List<Seat> availableSeats(TheaterRepository theaterRepository, SeatReservationRepository seatReservationRepository, Movie movie) {
            List<Theater> theaters = theaterRepository.select(movie.getCinemaName(), movie.getTheaterId());
            if (theaters.size() <= 0) {
                return Collections.emptyList();
            }
            Theater theater = theaters.get(0);
            List<Seat> seats = new ArrayList<>();
            List<SeatReservation> seatReservationList = seatReservationRepository.select(movie.getDate(), movie.getCinemaName(), movie.getTheaterId());
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

        private boolean isSeatTaken(List<SeatReservation> seatReservationList, Integer row, Integer seat) {
            for (SeatReservation reservation : seatReservationList) {
                if (reservation.getRow().equals(row) && reservation.getSeat().equals(seat)) {
                    return true;
                }
            }
            return false;
        }
    }

    private long falseReservations() {
        List<SeatReservation> seatReservations = SeatReservationRepository.getInstance().fetchAll();
        return seatReservations.stream()
                .filter(seatReservation1 -> seatReservations.stream().filter(seatReservation1::equals).count() > 1).count();
    }

}