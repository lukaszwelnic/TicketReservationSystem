package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Cinema {
    private String cinemaName;
    private Integer numberOfTheaters;

    public Cinema(Row row) {
        this.cinemaName = row.getString("cinema_name");
        this.numberOfTheaters = row.getInt("number_of_theaters");
    }
}
