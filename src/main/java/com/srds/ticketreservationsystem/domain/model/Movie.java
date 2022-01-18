package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Movie {
    private String cinema_name;
    private String movie_name;
    private Integer theater_id;
    //timestamp
    private String date;

    public Movie(Row row) {
        cinema_name = row.getString("cinema_name");
        movie_name = row.getString("movie_name");
        theater_id = row.getInt("theater_id");
        date = row.getString("date");
    }
}