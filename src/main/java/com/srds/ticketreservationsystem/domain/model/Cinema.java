package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cinema {
    private String cinemaName;
    private Integer numberOfTheaters;

    public Cinema(Row row) {
        cinemaName = row.getString("cinema_name");
        numberOfTheaters = row.getInt("number_of_theaters");
    }
}