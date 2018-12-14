package service;

import dao.RecordDao;
import entity.Record;
import utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportService {
    private RecordDao rDao = new RecordDao();

    public List<Record> listThisMonthRecords() {
        List<Record> monthRawData= rDao.list(DateUtil.monthBegin(), DateUtil.monthEnd());
        List<Record> result= new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.gapDay(DateUtil.monthBegin(),DateUtil.monthEnd());
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime() ;
            int daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.setSpend(daySpend);
            result.add(r);
        }
        return result;
    }
    public int getDaySpend(Date d,List<Record> monthRawData){
        int daySpend = 0;
        for (Record record : monthRawData) {
            if(record.getDate().equals(d))
                daySpend+=record.getSpend();
        }
        return daySpend;
    }
}
