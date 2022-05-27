package service;

import dao.GroupDao;
import dao.api.ICRUDDao;
import dto.Group;

import java.util.List;

public class GroupService implements ICRUDDao<Group> {
    private static final GroupService instance = new GroupService();

    private final GroupDao dao;

    private GroupService() {
        this.dao = GroupDao.getInstance();
    }

    public void add(Group group) {
        this.dao.add(group);
    }

    public List<Group> readAll() {
        return this.dao.readAll();
    }

    public Group get(int id) {
        return this.dao.get(id);
    }

    public void update(int id, Group group) {

    }

    public void delete(int id) {

    }
    public static GroupService getInstance() {
        return instance;
    }
}
