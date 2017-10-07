package kr.bluepoet.videoshop.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by bluepoet on 2017. 10. 7..
 */
public class DateUtils {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime parse(String releaseDate) {
        return LocalDateTime.parse(releaseDate, formatter);
    }
}
