package dao;

import dao.api.ICRUDDao;
import dto.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements AutoCloseable, ICRUDDao<Student> {

    private static final StudentDao instance = new StudentDao();

    public StudentDao() {
    }

    public List<Student> readAll(){
        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    student_id,\n" +
                             "    student_name,\n" +
                             "    student_age,\n" +
                             "    score,\n" +
                             "    olympic_gamer\n" +
                             "FROM\n" +
                             "    students\n;"
             );
        ) {
            while (resultSet.next()){
                students.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public Student get(int id){
        try (Connection connection = dao.ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    student_id,\n" +
                             "    student_name,\n" +
                             "    student_age,\n" +
                             "    score,\n" +
                             "    olympic_gamer\n" +
                             "FROM\n" +
                             "    students\n" +
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

    public void add(Student student) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                     "students(student_name, student_age, score, olympic_gamer) " +
                     "VALUES (?, ?, ?, ?)");
        ) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getScore());
            statement.setBoolean(4, student.isOlympicGamer());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void update(int id, Student item) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE students " +
                     "SET student_name=?, student_age=?, score=?, olympic_gamer=? WHERE id=?")
        ) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getAge());
            statement.setDouble(3, item.getScore());
            statement.setBoolean(4, item.isOlympicGamer());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    private Student map(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("student_id"),
                rs.getString("student_name"),
                rs.getInt("student_age"),
                rs.getDouble("score"),
                rs.getBoolean("olympic_gamer")
        );
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static StudentDao getInstance() {
        return instance;
    }
}
