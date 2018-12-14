package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MysqlUtil {
    /**
     * 备份数据库
     *
     * @param mysqlPath  mysql的路径
     * @param backupFile 备份的文件路径
     */
    public static void backup(String mysqlPath, String backupFile) throws IOException {
        String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s -hlocalhost -P%d %s -r\"%s\"";
        String command = String.format(commandFormat, mysqlPath, DBUtil.user, DBUtil.password, DBUtil.port, DBUtil.database, backupFile);
        System.out.println("MysqlUtil:command-->" + command);
        Runtime.getRuntime().exec(command);
    }

    /**
     * 还原数据库
     *
     * @param mysqlPath  mysql的路径
     * @param backupFile 备份的文件路径
     */
    public static void recover(String mysqlPath, String backupFile) throws IOException {
        String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s %s";
        String command = String.format(commandFormat, mysqlPath, DBUtil.user, DBUtil.password, DBUtil.database);
        BufferedReader bReader = null;
        OutputStreamWriter osWriter = null;
        try {
            Process exec = Runtime.getRuntime().exec(command);
            OutputStream os = exec.getOutputStream();
            StringBuilder sb = new StringBuilder();
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(backupFile), StandardCharsets.UTF_8));
            String inStr;
            while ((inStr = bReader.readLine()) != null) {
                sb.append(inStr).append("\r\n");
            }
            osWriter = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            osWriter.write(sb.toString());
        } finally {
            DBUtil.close(bReader);
            DBUtil.close(osWriter);
        }

    }
}
