package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Theater {
    private String cinemaName;
    private Integer theaterId;
    private Integer numberOfRows;
    private Integer numberOfSeats;

    public Theater(Row row) {
        this.cinemaName = row.getString("cinema_name");
        this.theaterId = row.getInt("theater_id");
        this.numberOfRows = row.getInt("number_of_rows");
        this.numberOfSeats = row.getInt("number_of_seats");
    }
}
