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

    protected String SELECT;
    protected String FETCH_ALL;
    protected String DELETE_ALL;
    protected String UPSERT;

    protected final Session session;

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

    public abstract void upsert(T object);

    public void upsert(Object... values) {
        BoundStatement boundStatement = new BoundStatement(session.prepare(UPSERT));
        boundStatement.bind(values);
        try {
            session.execute(boundStatement);
        } catch (Exception exception) {
            throw new CannotExecuteStatementException(UPSERT, exception);
        }
    }

    public List<T> executeSelect(BoundStatement bs) {
        ResultSet resultSet;
        try {
            resultSet = session.execute(bs);
        } catch (Exception exception) {
            throw new CannotExecuteStatementException(SELECT, exception);
        }
        List<Row> rows = resultSet.all();
        return rows
                .stream()
                .map(this::decodeModel)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        BoundStatement boundStatement = new BoundStatement(session.prepare(DELETE_ALL));
        try {
            session.execute(boundStatement);
        } catch (Exception exception) {
            throw new CannotExecuteStatementException(DELETE_ALL, exception);
        }
    }

    protected abstract T decodeModel(Row row);

}