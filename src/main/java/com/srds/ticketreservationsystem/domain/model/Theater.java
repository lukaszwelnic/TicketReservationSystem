package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Theater {
    private String cinemaName;
    private Integer theaterId;
    private Integer numberOfRows;
    private Integer numberOfSeats;

    public Theater(Row row) {
        cinemaName = row.getString("cinema_name");
        theaterId = row.getInt("theater_id");
        numberOfRows = row.getInt("number_of_rows");
        numberOfSeats = row.getInt("number_of_seats");
    }
}