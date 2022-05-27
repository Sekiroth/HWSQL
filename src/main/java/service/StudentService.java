package service;

import dao.StudentDao;
import dao.api.ICRUDDao;
import dto.Student;

import java.util.List;

public class StudentService implements ICRUDDao<Student> {
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
        this.dao.update(id, student);
    }

    public void delete(int id) {
        this.dao.delete(id);
    }

    public static StudentService getInstance() {
        return instance;
    }
}
