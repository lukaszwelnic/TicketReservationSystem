package com.srds.ticketreservationsystem.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import lombok.Getter;

@Getter
public class CassandraConnector implements AutoCloseable {
    private Cluster cluster;
    private Session session;

    public CassandraConnector() {
        connect();
    }

    public void connect() {
        cluster = Cluster.builder()
                .addContactPoint(Property.get("contact_point"))
                .withCredentials(Property.get("database_user"), Property.get("database_password"))
                .build();
        session = cluster.connect(Property.get("keyspace"));
    }

    @Override
    public void close() {
        session.close();
        cluster.close();
    }
}
