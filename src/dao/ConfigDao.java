package dao;

import entity.Config;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class ConfigDao extends BaseDao {
    public Config add(String key, String value) {
        Config config = null;
        String sql = String.format("insert into config values(null,%s,%s)", key, value);
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery(sql);
            if (resultSet.next()) {
                config = new Config();
                config.setId(resultSet.getInt(1));
                config.setKey(resultSet.getString(2));
                config.setValue(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    DBUtil.close(resultSet.getStatement());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return config;
    }

    public boolean delete(int id) {
        String sql = String.format("delete from config where id = %d", id);
        return execute(sql);
    }

    public boolean delete(String name) {
        String sql = String.format("delete from config where name = %s", name);
        return execute(sql);
    }

    public boolean update(String key, String value) {
        String sql = String.format("update config set value = %s where _key = %s", value, key);
        return execute(sql);
    }

    public boolean update(Config category) {
        String sql = String.format("update config set value = %s,_key=%s where id = %s", category.getValue(), category.getKey(), category.getId());
        return execute(sql);
    }
    public boolean updateOrAdd(Config category) {

        String sql = String.format("insert into config values(%d,'%s','%s') on duplicate key update value = '%s'", category.getId(),category.getKey(),category.getValue(),  category.getValue());
        return execute(sql);
    }

    public Config get(String key) {
        Config config = null;
        String sql = String.format("select * from config where _key= '%s'", key);
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery(sql);
            if (resultSet.next()) {
                config = new Config();
                config.setId(resultSet.getInt(1));
                config.setKey(resultSet.getString(2));
                config.setValue(resultSet.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    DBUtil.close(resultSet.getStatement());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return config;
    }
}
