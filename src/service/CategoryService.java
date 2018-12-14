package service;

import dao.CategoryDao;
import dao.RecordDao;
import entity.Category;
import entity.Record;

import java.util.Collections;
import java.util.List;

public class CategoryService {
    RecordDao recordDao = new RecordDao();

    public List<Category> list() {
        List<Category> list = CategoryDao.list();
        //根据使用率排序
        for (Category category : list) {
            List<Record> records = recordDao.list(category.getId());
            category.setRecordNumber(records.size());
        }
        list.sort((o1, o2) -> o2.getRecordNumber() - o1.getRecordNumber());
        return list;
    }

    public boolean add(String name) {
        return CategoryDao.add(name);
    }
    public boolean delete(String name) {
        return CategoryDao.delete(name);
    }
    public boolean update(Category category) {
        return CategoryDao.update(category);
    }
}
