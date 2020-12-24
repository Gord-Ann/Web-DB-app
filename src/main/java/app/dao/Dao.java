package app.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    T get(long id);
    List<T> list();
    long save(T t) throws SQLException;
    void delete(long id) throws SQLException;
}