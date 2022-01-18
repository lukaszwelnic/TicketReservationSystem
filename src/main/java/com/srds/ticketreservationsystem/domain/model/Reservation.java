package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Setter
@Getter
public class Reservation {
    private String client_nick;
    private String movie_name;
    //timestamp
    private String reservation_date;
    private Integer row;
    private Integer seat;
    private String cinema_name;
    private Integer theater_id;
    private Float price;
    //timestamp
    private String created_at;

    public Reservation(Row row_) {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        client_nick = row_.getString("client_nick");
        movie_name = row_.getString("movie_name");
        reservation_date = row_.getString("reservation_date");
        row = row_.getInt("row");
        seat = row_.getInt("seat");
        cinema_name = row_.getString("cinema_name");
        theater_id = row_.getInt("theater_id");
        price = row_.getFloat("price");
        created_at = row_.getString("created_at");
    }
}
