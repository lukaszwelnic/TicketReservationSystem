package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class ClientReservation {
    private String clientNick;
    private String movieName;
    private Date reservationDate;
    private Integer row;
    private Integer seat;
    private String cinemaName;
    private Integer theaterId;
    private Float price;
    private Date createdAt;

    public ClientReservation(Row row) {
        this.clientNick = row.getString("client_nick");
        this.movieName = row.getString("movie_name");
        this.reservationDate = row.getTimestamp("reservation_date");
        this.row = row.getInt("row");
        this.seat = row.getInt("seat");
        this.cinemaName = row.getString("cinema_name");
        this.theaterId = row.getInt("theater_id");
        this.price = row.getFloat("price");
        this.createdAt = row.getTimestamp("created_at");
    }
}
