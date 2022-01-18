package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cinema {
    private String cinema_name;
    private Integer number_of_theaters;

    public Cinema(Row row) {
        cinema_name = row.getString("cinema_name");
        number_of_theaters = row.getInt("number_of_theaters");
    }
}