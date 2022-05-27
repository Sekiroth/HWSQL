package dao;

import dao.api.ICRUDDao;
import dto.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDao implements ICRUDDao<Table> {
    private final static TableDao instance = new TableDao();
    @Override
    public List<Table> readAll() {
        List<Table> table = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    student_id,\n" +
                             "    group_id,\n" +
                             "FROM\n" +
                             "    table\n;"
             );
        ) {
            while (resultSet.next()){
                table.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return table;
    }

    @Override
    public Table get(int id) {
        try (Connection connection = dao.ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    student_id,\n" +
                             "    group_id\n" +
                             "FROM\n" +
                             "    table\n;" +
                             "WHERE student_id = " + id + ";"
             );
        ) {
            while (resultSet.next()){
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void add(Table item) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO\n" +
                     "  public.table(student_id, group_id)\n" +
                     "VALUES\n" +
                     "  (?, ?)")
        ) {
            statement.setInt(1, item.getStudentId());
            statement.setInt(2, item.getGroupId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void update(int id, Table item) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE\n" +
                     "  table\n" +
                     "SET\n" +
                     "  group_id = ?,\n" +
                     "WHERE\n" +
                     "  student_id = ?")
        ) {
            statement.setInt(1, id);
            statement.setInt(2, item.getStudentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM\n" +
                     "  table\n" +
                     "WHERE\n" +
                     "  student_id = ?")
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    public Table map(ResultSet rs) throws SQLException {
        return new Table(
                rs.getInt("student_id"),
                rs.getInt("group_id")
        );
    }

    public static TableDao getInstance() {
        return instance;
    }
}
