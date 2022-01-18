package com.srds.ticketreservationsystem;

import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.ClientReservation;
import com.srds.ticketreservationsystem.domain.repository.MovieRepository;
import com.srds.ticketreservationsystem.domain.repository.ClientReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class StressTest {
    private final List<Thread> threads = new ArrayList<>();
    private final int THREADS_NUM = 1000;
    private final int RESERVATIONS_PER_USER = 1000;

    private final ClientReservationRepository clientReservationRepository;
    private final MovieRepository movieRepository;

    public StressTest() {
        CassandraConnector connector = new CassandraConnector();
        this.clientReservationRepository = new ClientReservationRepository(connector);
        this.movieRepository = new MovieRepository(connector);
    }

    @BeforeEach
    public void setUp() {
        // TODO: delete all reservations
        clientReservationRepository.deleteAll();
    }

    @Test
    public void performanceTest() throws InterruptedException {
        clientReservationRepository.upsert(new ClientReservation("TESTOWY_NICK", "Scary movie", Date.from(Instant.now()), 1, 1, "Kino", 1, 1.2f, Date.from(Instant.now())));
//        for (int i = 0; i < THREADS_NUM; i++) {
//            Thread thread = new Thread(new RunnableThread("Testowy" + i));
//            threads.add(thread);
//            thread.start();
//        }
//        for (Thread thread : threads) {
//            thread.join();
//        }
    }

    private class RunnableThread implements Runnable {
        private final String nick;
        private final List<String> movies = new ArrayList<>();

        public RunnableThread(String nick) {
            this.nick = nick;
        }

        @Override
        public void run() {
            for (int i = 0; i < RESERVATIONS_PER_USER; i++) {

                // TODO: reserve movie
                movies.add(""); // TODO: add movie to list of reserved
            }
            int stats = 0;
            for (String movie : movies) {
                // TODO: Check if that movie is in database with nick
                // TODO: if yes then stats++
            }
        }
    }

}