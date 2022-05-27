package service;

import dao.TableDao;
import dao.api.ICRUDDao;
import dto.Table;

import java.util.List;

public class TableService implements ICRUDDao<Table> {
    private static final TableService instance = new TableService();
    private final TableDao dao;

    public TableService() {
        this.dao = TableDao.getInstance();
    }

    @Override
    public List<Table> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Table get(int id) {
        return this.dao.get(id);
    }

    @Override
    public void add(Table item) {
        this.dao.add(item);
    }

    @Override
    public void update(int id, Table item) {
        this.dao.update(id, item);
    }

    @Override
    public void delete(int id) {
        this.dao.delete(id);
    }
    public static TableService getInstance() {
        return instance;
    }
}
