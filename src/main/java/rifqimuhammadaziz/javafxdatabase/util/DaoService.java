package rifqimuhammadaziz.javafxdatabase.util;

import java.sql.SQLException;
import java.util.List;

public interface DaoService<T> {

    List<T> getAll() throws SQLException, ClassNotFoundException;

    int addData(T object) throws SQLException, ClassNotFoundException;

    int updateData(T object) throws SQLException, ClassNotFoundException;

    int deleteData(T object) throws SQLException, ClassNotFoundException;
}
