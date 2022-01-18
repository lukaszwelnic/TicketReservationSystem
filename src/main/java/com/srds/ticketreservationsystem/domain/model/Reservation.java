package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class Reservation {
    private String clientNick;
    private String movieName;
    private Date reservationDate;
    private Integer row;
    private Integer seat;
    private String cinemaName;
    private Integer theaterId;
    private Float price;
    private Date createdAt;

    public Reservation(Row row_) {
        clientNick = row_.getString("client_nick");
        movieName = row_.getString("movie_name");
        reservationDate = row_.getTimestamp("reservation_date");
        row = row_.getInt("row");
        seat = row_.getInt("seat");
        cinemaName = row_.getString("cinema_name");
        theaterId = row_.getInt("theater_id");
        price = row_.getFloat("price");
        createdAt = row_.getTimestamp("created_at");
    }
}
