package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.StudentDao;
import dto.Group;
import dto.Student;
import service.GroupService;
import service.StudentService;

import java.util.List;

public class Main2 {
    public static void main(String[] args) throws JsonProcessingException {
        GroupService groupService = GroupService.getInstance();
        groupService.add(new Group(0, "someGroup"));

        List<Group> groups = groupService.readAll();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(groups);
        System.out.println(s);
    }
}
