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

    public List<Group> readAll(){
        List<Group> groups = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    group_id,\n" +
                             "    group_number\n" +
                             "FROM\n" +
                             "    public.groups\n;"
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

    public Group get(int id){
        try (Connection connection = dao.ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    group_id,\n" +
                             "    group_number\n" +
                             "FROM\n" +
                             "    public.groups\n;" +
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

    public void add(Group group) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                     "public.groups(group_id, group_number) " +
                     "VALUES (?, ?)");
        ) {
            statement.setInt(1, group.getId());
            statement.setString(2, group.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void update(int id, Group item) {

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
