package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Theater {
    private String cinema_name;
    private Integer theater_id;
    private Integer number_of_rows;
    private Integer number_of_seats;

    public Theater(Row row) {
        cinema_name = row.getString("cinema_name");
        theater_id = row.getInt("theater_id");
        number_of_rows = row.getInt("number_of_rows");
        number_of_seats = row.getInt("number_of_seats");
    }
}