package utils;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static long DAY_MILLISECOND = 1000 * 60 * 60 * 24;

    /**
     * 获取月初。使用Calendar获取本月第一天。 在统计消费一览信息的时候，查看本月的消费数据，其实就是从数据库中去把从本月第一天到最后一天的数据查出来，再进行简单统计，所以需要一个获取本月第一天的方法。
     */
    public static Date monthBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取月末
     */
    public static Date monthEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    /**
     * 获取月末
     */
    public static Date today() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取两个时间相隔的天数
     */
    public static int gapDay(Date startDate, Date endDate) {

        long firstDayMilliSeconds = startDate.getTime();
        long lastDayMilliSeconds = endDate.getTime();

        return (int) ((lastDayMilliSeconds - firstDayMilliSeconds) / DAY_MILLISECOND) + 1;
    }
}
