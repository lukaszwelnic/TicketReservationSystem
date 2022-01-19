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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class StressTest {
    private final List<Thread> threads = new ArrayList<>();
    private final int THREADS_NUM = 500;

    private static final CassandraConnector cassandraConnector;
    private static final ReservationDAO reservationDAO;

    static {
        cassandraConnector = new CassandraConnector();
        CinemaRepository.setInstance(new CinemaRepository(cassandraConnector));
        ClientReservationRepository.setInstance(new ClientReservationRepository(cassandraConnector));
        MovieRepository.setInstance(new MovieRepository(cassandraConnector));
        SeatReservationRepository.setInstance(new SeatReservationRepository(cassandraConnector));
        TheaterRepository.setInstance(new TheaterRepository(cassandraConnector));
        reservationDAO = new ReservationDAO();
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
        // TODO: check how many same seats
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
            Random random = new Random();
            List<Movie> movies = MovieRepository.getInstance().fetchAll();
            Movie movie = movies.get(random.nextInt(movies.size()));
            List<Seat> seats = reservationDAO.availableSeats(movie);
            Seat seat = seats.get(random.nextInt(seats.size()));
            reservationDAO.reserveSeat(movie, seat, nick);
        }
    }

    private long falseReservations() {
        List<SeatReservation> seatReservations = SeatReservationRepository.getInstance().fetchAll();
        return seatReservations.stream().filter(seatReservation1 -> seatReservations.stream().filter(seatReservation1::equals).count() > 1).count();
    }

}