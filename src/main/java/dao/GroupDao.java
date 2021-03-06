package dao;

import dao.api.ICRUDDao;
import dto.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements AutoCloseable, ICRUDDao<Group> {

    private static final GroupDao instance = new GroupDao();

    public GroupDao() {
    }

    @Override
    public List<Group> readAll(){
        List<Group> groups = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    group_id,\n" +
                             "    group_number\n" +
                             "FROM\n" +
                             "    groups\n;"
             );
        ) {
            while (resultSet.next()){
                groups.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    public Group get(int id){
        try (Connection connection = dao.ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    group_id,\n" +
                             "    group_number\n" +
                             "FROM\n" +
                             "    groups\n" +
                             "WHERE group_id = " + id + ";"
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
    public void add(Group group) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO\n" +
                     "  groups(group_number)\n" +
                     "VALUES\n" +
                     "  (?)");
        ) {
            statement.setString(1, group.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void update(int id, Group item) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE\n" +
                     "  groups\n" +
                     "SET\n" +
                     "  group_number = ?,\n" +
                     "WHERE\n" +
                     "  group_id = ?")
        ) {
            statement.setString(1, item.getNumber());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM\n" +
                     "  groups\n" +
                     "WHERE\n" +
                     "  group_id = ?")
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    private Group map(ResultSet rs) throws SQLException {
        return new Group(
                rs.getInt("group_id"),
                rs.getString("group_number")
        );
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static GroupDao getInstance() {
        return instance;
    }
}
