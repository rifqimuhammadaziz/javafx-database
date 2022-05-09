package rifqimuhammadaziz.javafxdatabase.dao;

import rifqimuhammadaziz.javafxdatabase.entity.Department;
import rifqimuhammadaziz.javafxdatabase.entity.Faculty;
import rifqimuhammadaziz.javafxdatabase.util.DaoService;
import rifqimuhammadaziz.javafxdatabase.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DaoService<Department> {

    @Override
    public List<Department> getAll() throws SQLException, ClassNotFoundException {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String QUERY = "SELECT d.id, d.name, d.faculty_id, f.name AS faculty_name FROM department d JOIN faculty f ON d.faculty_id = f.id ";
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Faculty faculty = new Faculty();
                        faculty.setId(rs.getInt("faculty_id"));
                        faculty.setName(rs.getString("faculty_name"));

                        Department department = new Department();
                        department.setId(rs.getInt("id"));
                        department.setName(rs.getString("name"));
                        department.setFaculty(faculty);
                        departments.add(department);
                    }
                }
            }
        }
        return departments;
    }

    @Override
    public int addData(Department object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String QUERY = "INSERT INTO department(name, faculty_id) VALUES(?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, object.getName());
                ps.setInt(2, object.getFaculty().getId());
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
    public int updateData(Department object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String QUERY = "UPDATE department SET name = ?, faculty_id = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, object.getName());
                ps.setInt(2, object.getFaculty().getId());
                ps.setInt(3, object.getId());
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
    public int deleteData(Department object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String QUERY = "DELETE FROM department WHERE id = ?";
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
