package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.Row;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.domain.model.Theater;
import com.srds.ticketreservationsystem.exception.RepositoryNotInitializedException;
import lombok.Setter;

public class TheaterRepository extends GenericRepository<Theater> {
    @Setter
    private static TheaterRepository instance;

    public TheaterRepository(CassandraConnector connector) {
        super(connector);
        FETCH_ALL = "SELECT * FROM theater;";
        UPSERT = "UPDATE theater SET number_of_rows = ?, number_of_seats = ? where cinema_name = ? and theater_id = ?;";
        DELETE_ALL = "TRUNCATE theater;";
    }

    @Override
    public void upsert(Theater theater) {
        upsert(theater.getNumberOfRows(), theater.getNumberOfSeats(),
                theater.getCinemaName(), theater.getTheaterId());
    }

    @Override
    public void delete(Theater theater) {

    }

    @Override
    protected Theater decodeModel(Row row) {
        return new Theater(row);
    }

    public static TheaterRepository getInstance() {
        if (instance == null) {
            throw new RepositoryNotInitializedException();
        }
        return instance;
    }
}
