package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class Movie {
    private String cinema_name;
    private String movie_name;
    private Integer theater_id;
    private Date date;

    public Movie(Row row) {
        cinema_name = row.getString("cinema_name");
        movie_name = row.getString("movie_name");
        theater_id = row.getInt("theater_id");
        date = row.getTimestamp("date");
    }
}