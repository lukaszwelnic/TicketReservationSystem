package com.srds.ticketreservationsystem.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import lombok.Getter;

@Getter
public class CassandraConnector {
    private Cluster cluster;
    private Session session;

    public void connect() {
        cluster = Cluster.builder()
                .addContactPoint(Property.get("contact_point"))
                .build();
        session = cluster.connect(Property.get("keyspace"));
    }

    public void disconnect() {
        session.close();
        cluster.close();
    }

}
