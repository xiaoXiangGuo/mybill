package service;

import dao.RecordDao;
import entity.Record;

public class RecordService {
    private RecordDao rDao = new RecordDao();

    public boolean add(Record record) {
        return rDao.add(record);
    }
}
