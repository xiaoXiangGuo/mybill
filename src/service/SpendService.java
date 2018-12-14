package service;

import dao.ConfigDao;
import dao.RecordDao;
import entity.Record;
import gui.panelData.SpendData;
import utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpendService {
    private RecordDao rDao = new RecordDao();


    public SpendData getSpendData() {
        SpendData spendData = new SpendData();
        List<Record> months = rDao.list(DateUtil.monthBegin(), DateUtil.monthEnd());
        //预算
        int budget = Integer.parseInt(new ConfigService().get(ConfigService.budget));
        Date today = DateUtil.today();
        for (Record record : months) {
            spendData.monthSpend += record.getSpend();
            if (record.getDate().equals(today)) {
                spendData.todaySpend += record.getSpend();
            }
        }
        //日均消费
        spendData.avgSpendPerDay = spendData.monthSpend / DateUtil.gapDay(DateUtil.monthBegin(), DateUtil.today());
        //本月剩余
        spendData.monthAvailable = budget - spendData.monthSpend;


        spendData.monthLeftDay = DateUtil.gapDay(DateUtil.today(), DateUtil.monthEnd());

        spendData.dayAvgAvailable = spendData.monthAvailable / spendData.monthLeftDay;

        spendData.usagePercentage = spendData.monthSpend * 100 / budget;

        spendData.isOverSpend = spendData.monthAvailable < 0;
        return spendData;
    }
}
