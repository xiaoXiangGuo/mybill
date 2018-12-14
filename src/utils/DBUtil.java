package utils;

import java.sql.*;

public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "hutubill";
    static String encoding = "utf-8";

    static String user = "root";
    static String password = "admin";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(AutoCloseable c) {
        if (null != c) {
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement s) {
        if (null == s) {
            return;
        }
        try {
            Connection connection = s.getConnection();
            if (connection != null && !connection.isClosed())
                close(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close((AutoCloseable) s);
        }
    }


}
