package dao.api;

import dto.Student;

import java.util.List;

public interface ICRUDDao<T> {
    List<T> readAll();
    T get(int id);
    void add(T item);
    void update(int id, T item);
}
