package dao;

import entity.Category;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends BaseDao{
    public static int getCount() {
        int count = 0;
        String sql = "select count(*) from category ";
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery(sql);
            if (resultSet.next()) {
                count = resultSet.getInt(1);
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
        return count;
    }

    public static boolean add(String name) {
        String sql = String.format("insert into category values(null,'%s')", name);
        return execute(sql);
    }

    public static boolean delete(int id) {
        String sql = String.format("delete from category where id = %d", id);
        return execute(sql);
    }

    public static boolean delete(String name) {
        String sql = String.format("delete from category where name = '%s'", name);
        return execute(sql);
    }

    public static boolean update(Category category) {
        String sql = String.format("update category set name = '%s' where id = %d", category.getName(), category.getId());
        return execute(sql);
    }

    public static Category get(int id) {
        String sql = String.format("select * from category where id = %d", id);
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery(sql);
            if (resultSet.next()) {
                Category category = new Category();
                category.setName(resultSet.getString(2));
                category.setId(id);
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != resultSet)
                    DBUtil.close(resultSet.getStatement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Category> list() {
        return list(0, Integer.MAX_VALUE);
    }

    public static List<Category> list(int start, int count) {
        List<Category> list = new ArrayList<>();
        String sql = String.format("select * from category order by id desc limit %d,%d", start, count);
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery(sql);
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != resultSet)
                    DBUtil.close(resultSet.getStatement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
