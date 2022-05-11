package rifqimuhammadaziz.javafxdatabase.util;

import java.util.List;

public interface HibernateDaoService<T> {

    int addData(T object);

    int deleteData(T object);

    int updateData(T object);

    List<T> getAll();
}
