package service;

import dao.ConfigDao;
import entity.Config;

public class ConfigService {
    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";//用于备份数据库
    public static final String default_budget = "500";

    private ConfigDao cDao = new ConfigDao();
    public String get(String key) {
        Config config = cDao.get(key);
        if (null == config)
            return "";
        return config.getUnescapeValue();
    }

    public void update(String key, String value) {
        Config config = cDao.get(key);
        config.setValue(value);
        cDao.update(config);
    }

    public void updateOrAdd(String key, String value) {
        Config config = cDao.get(key);
        if (null == config) {
            config = new Config();
            config.setKey(key);
            config.setValue(value);
        } else {
            config.setValue(value);
        }
        cDao.updateOrAdd(config);
    }

    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }
}
