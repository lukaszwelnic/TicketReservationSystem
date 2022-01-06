package com.srds.ticketreservationsystem.domain.model;

import com.datastax.driver.core.Row;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Reservation {
    private Integer id;
    private String name;

    public Reservation(Row row) {
        id = row.getInt("id");
        name = row.getString("name");
    }
}
