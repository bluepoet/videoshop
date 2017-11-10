package kr.bluepoet.junit5.videoshop.util;

import kr.bluepoet.videoshop.domain.Renter;
import kr.bluepoet.videoshop.domain.RenterGrade;
import kr.bluepoet.videoshop.domain.Video;

import java.util.Arrays;
import java.util.List;

public class TestDatas {
    public static Renter NORMAL_RENTER = new Renter("tester", RenterGrade.NORMAL, "2017-10-06 00:00:00");
    public static Renter SILVER_RENTER = new Renter("renter", RenterGrade.SILVER, "2014-10-06 00:00:00");
    public static Renter GOLD_RENTER = new Renter("bluepoet", RenterGrade.GOLD, "2012-10-06 00:00:00");

    public static String NO_EVENT_TIME = "2017-10-11 10:30:33";
    public static String MONDAY_EVENT_TIME = "2017-10-09 10:30:33";
    public static String WEEKEND_EVENT_TIME = "2017-10-14 18:30:33";

    public static List<Video> createVideos() {
        Video video1 = new Video("alien", 1000, "2017-10-06 00:00:00");
        Video video2 = new Video("terminator", 2000, "2017-10-07 00:00:00");
        Video video3 = new Video("toys", 3000, "2017-10-08 00:00:00");
        return Arrays.asList(video1, video2, video3);
    }

    public static int getTotalPrice() {
        return createVideos().stream().mapToInt(v -> v.getPrice()).sum();
    }
}
