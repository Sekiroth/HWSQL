package service;

import dao.StudentDao;
import dto.Student;

import java.util.List;

public class StudentService {
    private static final StudentService instance = new StudentService();

    private final StudentDao dao;

    private StudentService() {
        this.dao = StudentDao.getInstance();
    }

    public void add(Student student) {
        this.dao.add(student);
    }

    public List<Student> readAll() {
        return this.dao.readAll();
    }

    public Student get(int id) {
        return this.dao.get(id);
    }

    public void update(int id, Student student) {

    }

    public void delete(int id) {

    }
    public static StudentService getInstance() {
        return instance;
    }
}
