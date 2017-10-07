package kr.bluepoet.videoshop.domain;

import static kr.bluepoet.videoshop.util.DateUtils.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Created by bluepoet on 2017. 10. 7..
 */
public class TimeDiscountRule implements DiscountRule {
    public static final int MONDAY_EVENT_HOUR = 10;
    public static final int WEEKEND_EVENT_HOUR = 18;
    public static final double MONDAY_DISCOUNT_RATE = 0.03;
    public static final double WEEKEND_DISCOUNT_RATE = 0.05;
    private LocalDateTime nowTime;
    private int totalPrice;

    public TimeDiscountRule(String nowTime, int totalPrice) {
        this.nowTime = parse(nowTime);
        this.totalPrice = totalPrice;
    }

    @Override
    public int discount() {
        if (isMondayEventTime()) {
            return (int) (totalPrice * MONDAY_DISCOUNT_RATE);
        }

        if (isWeekendEventTime()) {
            return (int) (totalPrice * WEEKEND_DISCOUNT_RATE);
        }

        return 0;
    }

    public boolean isMondayEventTime() {
        if (nowTime.getDayOfWeek() == DayOfWeek.MONDAY && nowTime.getHour() == MONDAY_EVENT_HOUR) {
            return true;
        }

        return false;
    }

    public boolean isWeekendEventTime() {
        if ((nowTime.getDayOfWeek() == DayOfWeek.SATURDAY || nowTime.getDayOfWeek() == DayOfWeek.SUNDAY)
                && nowTime.getHour() == WEEKEND_EVENT_HOUR) {
            return true;
        }

        return false;
    }
}
