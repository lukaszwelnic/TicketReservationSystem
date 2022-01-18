package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class SeatReservation {
    private Instant date;
    private String cinemaName;
    private Integer theaterId;
    private Integer row;
    private Integer seat;

    public SeatReservation(Row row) {
        this.date = row.getTimestamp("date").toInstant();
        this.cinemaName = row.getString("cinema_name");
        this.theaterId = row.getInt("theater_id");
        this.row = row.getInt("row");
        this.seat = row.getInt("seat");
    }
}
