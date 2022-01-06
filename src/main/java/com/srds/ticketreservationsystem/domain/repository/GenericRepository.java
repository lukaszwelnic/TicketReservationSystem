package com.srds.ticketreservationsystem.domain.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.srds.ticketreservationsystem.config.CassandraConnector;
import com.srds.ticketreservationsystem.exception.CannotExecuteStatementException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericRepository<T> {

    protected String FETCH_ALL;
    protected String SELECT;
    protected String DELETE;
    protected String UPDATE;

    private final Session session;

    public GenericRepository(CassandraConnector connector) {
        session = connector.getSession();
    }

    public List<T> fetchAll() {
        BoundStatement boundStatement = new BoundStatement(session.prepare(FETCH_ALL));
        ResultSet resultSet;
        try {
            resultSet = session.execute(boundStatement);
        } catch (Exception exception) {
            throw new CannotExecuteStatementException(FETCH_ALL, exception);
        }
        List<Row> rows = resultSet.all();
        return rows
                .stream()
                .map(this::decodeModel)
                .collect(Collectors.toList());
    }

    protected T decodeModel(Row row) {
        return null;
    }

}
