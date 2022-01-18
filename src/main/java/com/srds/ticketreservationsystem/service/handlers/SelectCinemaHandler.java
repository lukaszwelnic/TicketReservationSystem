package com.srds.ticketreservationsystem.service.handlers;

import com.srds.ticketreservationsystem.domain.model.Cinema;
import com.srds.ticketreservationsystem.domain.repository.CinemaRepository;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class SelectCinemaHandler extends BaseUIHandler {
    private static SelectCinemaHandler instance;
    @Getter
    private Cinema cinema;

    @Override
    public void handle() {
        List<Cinema> cinemas = CinemaRepository.getInstance().fetchAll();
        int movieIndex = chooseCinema(cinemas);
        if (movieIndex > cinemas.size()) {
            setNext(ExitHandler.newInstance());
        } else {
            cinema = cinemas.get(movieIndex - 1);
            setNext(MovieHandler.newInstance());
        }
        super.handle();
    }

    private int chooseCinema(List<Cinema> cinemas) {
        options = cinemas.stream()
                .map(Cinema::getCinemaName)
                .collect(Collectors.toList());
        options.add("Wyjdź");
        System.out.println("Kina:");
        return getUserResponse();
    }

    public static SelectCinemaHandler newInstance() {
        if (instance == null) {
            instance = new SelectCinemaHandler();
        }
        return instance;
    }
}
