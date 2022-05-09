package rifqimuhammadaziz.javafxdatabase.dao;

import rifqimuhammadaziz.javafxdatabase.entity.Faculty;
import rifqimuhammadaziz.javafxdatabase.util.DaoService;
import rifqimuhammadaziz.javafxdatabase.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyDaoImpl implements DaoService<Faculty> {

    @Override
    public List<Faculty> getAll() throws SQLException, ClassNotFoundException {
        List<Faculty> faculties = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String QUERY = "SELECT id, name FROM faculty";
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Faculty faculty = new Faculty();
                        faculty.setId(rs.getInt("id"));
                        faculty.setName(rs.getString("name"));
                        faculties.add(faculty);
                    }
                }
            }
        }
        return faculties;
    }

    @Override
    public int addData(Faculty object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String QUERY = "INSERT INTO faculty(name) VALUES(?)";
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, object.getName());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Faculty object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String QUERY = "UPDATE faculty SET name = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, object.getName());
                ps.setInt(2, object.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int deleteData(Faculty object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String QUERY = "DELETE FROM faculty WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setInt(1, object.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
}
