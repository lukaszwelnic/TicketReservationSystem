package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class SeatReservation {
    private Date date;
    private String cinemaName;
    private Integer theaterId;
    private String clientNick;
    private String movieName;
    private Integer row;
    private Integer seat;

    public SeatReservation(Row row) {
        this.date = row.getTimestamp("date");
        this.cinemaName = row.getString("cinema_name");
        this.theaterId = row.getInt("theater_id");
        this.clientNick = row.getString("client_nick");
        this.movieName = row.getString("movie_name");
        this.row = row.getInt("row");
        this.seat = row.getInt("seat");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SeatReservation){
            SeatReservation seatReservation = (SeatReservation) obj;
            return seatReservation.cinemaName.equals(this.cinemaName) &&
                    seatReservation.date.equals(this.date) &&
                    seatReservation.theaterId.equals(this.theaterId) &&
                    seatReservation.movieName.equals(this.movieName) &&
                    seatReservation.row.equals(this.row) &&
                    seatReservation.seat.equals(this.seat);
        }
        return false;
    }
}
