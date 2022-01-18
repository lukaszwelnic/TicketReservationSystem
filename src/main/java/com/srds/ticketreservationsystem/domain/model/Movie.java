package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class Movie {
    private String cinemaName;
    private String movieName;
    private Integer theaterId;
    private Date date;

    public Movie(Row row) {
        cinemaName = row.getString("cinema_name");
        movieName = row.getString("movie_name");
        theaterId = row.getInt("theater_id");
        date = row.getTimestamp("date");
    }
}
