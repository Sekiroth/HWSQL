package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ConnectionFactory;
import dto.Student;
import service.StudentService;

import java.sql.*;
import java.util.List;

public class Main1 {
    public static void main(String[] args) throws JsonProcessingException {


        StudentService studentService = StudentService.getInstance();
//        studentService.add(new Student(0, "AAA", 15, 2, true));
        studentService.update(1, new Student(1, "BBB", 15, 2, true));
        List<Student> students = studentService.readAll();

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(students);
        System.out.println(s);
    }
}
