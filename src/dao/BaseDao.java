package dao;

import utils.DBUtil;

import java.sql.*;

public class BaseDao {
    public static boolean execute(String sql) {
        Connection conn = null;
        Statement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.createStatement();
            int i = ps.executeUpdate(sql);
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps);
        }
        return false;
    }

    /**
     * 需要手动关闭链接
     */
    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = null;
        Statement ps = null;
        conn = DBUtil.getConnection();
        ps = conn.createStatement();
        return ps.executeQuery(sql);
    }
}
