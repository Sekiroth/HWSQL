package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Table;
import service.GroupService;
import service.StudentService;
import service.TableService;

import java.util.List;

public class Main3 {
    public static void main(String[] args) throws JsonProcessingException {
        TableService tableService = TableService.getInstance();
        StudentService studentService = StudentService.getInstance();
        GroupService groupService = GroupService.getInstance();
        tableService.add(new Table(studentService.get(9).getId(), groupService.get(1).getId()));

        List<Table> tables = tableService.readAll();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(tables);
        System.out.println(s);
    }
}
