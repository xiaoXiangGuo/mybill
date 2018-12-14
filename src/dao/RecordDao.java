package dao;

import entity.Record;
import utils.DBUtil;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecordDao extends BaseDao {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public boolean add(Record record) {
        String sql = String.format("insert into record values(null,%d,%d,'%s','%s')", record.getSpend(), record.getCid(), record.getComment(), sdf.format(record.getDate()));
        return execute(sql);
    }

    public List<Record> list(int cid) {
        ArrayList<Record> list = new ArrayList<>();
        String sql = String.format("select * from record where cid = %d", cid);
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery(sql);
            while (resultSet.next()) {
                Record record = new Record();
                record.setId(resultSet.getInt(1));
                record.setCid(resultSet.getInt("cid"));
                record.setSpend(resultSet.getInt("spend"));
                record.setComment(resultSet.getString("comment"));
                record.setDate(resultSet.getDate("date"));
                list.add(record);
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
        return list;
    }

    public List<Record> list(Date start, Date end) {
        ArrayList<Record> list = new ArrayList<>();
        String sql = String.format("select * from record where date >= '%s' and date <= '%s'", sdf.format(start), sdf.format(end));
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery(sql);
            while (resultSet.next()) {
                Record record = new Record();
                record.setId(resultSet.getInt("id"));
                record.setSpend(resultSet.getInt("spend"));
                record.setCid(resultSet.getInt("cid"));
                record.setComment(resultSet.getString("comment"));
                record.setDate(resultSet.getDate("date"));
                list.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet)
                try {
                    DBUtil.close(resultSet.getStatement());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return list;
    }
}
