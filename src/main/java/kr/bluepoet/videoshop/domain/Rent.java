package kr.bluepoet.videoshop.domain;

import org.apache.commons.collections4.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static kr.bluepoet.videoshop.util.DateUtils.parse;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class Rent {
    private static final int DEFAULT_RENT_DAYS = 3;
    private static final int ONE_DAY_DELAY_SECONDS = 86400 * DEFAULT_RENT_DAYS;
    private static final int SEVEN_DAY_DELAY_SECONDS = 604800 + ONE_DAY_DELAY_SECONDS;
    private static final int THIRTY_DAY_DELAY_SECONDS = 2592000 + ONE_DAY_DELAY_SECONDS;
    public static final double THIRTY_DAY_DELAY_RATE = 0.3;
    public static final double SEVEN_DAY_DELAY_RATE = 0.1;
    public static final double ONE_DAY_DELAY_RATE = 0.05;

    private Long id;
    private List<Video> videos;
    private Renter renter;
    private List<DiscountRule> discountRules;
    private LocalDateTime rentDate;

    public Rent() {
    }

    public Rent(Long id, List<Video> videos, Renter renter, LocalDateTime rentDate) {
        this.id = id;
        this.videos = videos;
        this.renter = renter;
        this.rentDate = rentDate;
    }

    public void setDiscountRules(List<DiscountRule> discountRules) {
        this.discountRules = discountRules;
    }

    public void addVideo(List<Video> videos) {
        this.videos = videos;
    }

    public int calculateRentPrice() {
        int totalPrice = 0;

        for (Video video : videos) {
            totalPrice += video.getPrice();
        }

        if (CollectionUtils.isNotEmpty(discountRules)) {
            return applyDiscountRule(totalPrice);
        }

        return totalPrice;
    }

    private int applyDiscountRule(int totalPrice) {
        int discountPrice = 0;
        for (DiscountRule discountRule : discountRules) {
            discountPrice += discountRule.discount();
        }

        return totalPrice - discountPrice;
    }

    public void addRenter(Renter renter) {
        this.renter = renter;
    }

    public int calculateDelayMoney(LocalDateTime nowTime) {
        long rentDurationSeconds = Duration.between(rentDate, nowTime).getSeconds();
        int totalPrice = calculateRentPrice();

        if (isOverdueSeconds(rentDurationSeconds, THIRTY_DAY_DELAY_SECONDS)) {
            return (int) (totalPrice * THIRTY_DAY_DELAY_RATE);
        }

        if (isOverdueSeconds(rentDurationSeconds, SEVEN_DAY_DELAY_SECONDS)) {
            return (int) (totalPrice * SEVEN_DAY_DELAY_RATE);
        }

        if (isOverdueSeconds(rentDurationSeconds, ONE_DAY_DELAY_SECONDS)) {
            return (int) (totalPrice * ONE_DAY_DELAY_RATE);
        }
        return 0;
    }

    private boolean isOverdueSeconds(long rentDurationSeconds, long delaySeconds) {
        return rentDurationSeconds > delaySeconds;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = parse(rentDate);
    }
}
