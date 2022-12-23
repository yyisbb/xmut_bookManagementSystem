package wang.gravity.bookmanagementsystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date) {
        return new SimpleDateFormat(DEFAULT_FORMAT).format(date);
    }
}
