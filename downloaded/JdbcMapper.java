package org.otus.education.jdbc.mapper;

import java.sql.Connection;
import java.util.Optional;

public interface JdbcMapper<T> {
    long insert(T objectData, Connection connection);

    void update(T objectData, Connection connection);

    void insertOrUpdate(T objectData, Connection connection);

    Optional<T> findById(Object id, Connection connection);
}
