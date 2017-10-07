package kr.bluepoet.videoshop.domain;

import java.time.LocalDateTime;
import java.util.List;

import static kr.bluepoet.videoshop.util.DateUtils.parse;

/**
 * Created by bluepoet on 2017. 10. 7..
 */
public class ReleaseDateDiscountRule implements DiscountRule {
    private static final double ONE_YEAR_DISCOUNT_RATE = 0.03;
    private static final double THREE_YEAR_DISCOUNT_RATE = 0.05;
    private static final double FIVE_YEAR_DISCOUNT_RATE = 0.08;
    private List<Video> videos;

    public ReleaseDateDiscountRule(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public int discount() {
        int discountPrice = 0;
        for (Video video : videos) {
            if (isPastFiveYear(video.getReleaseDate())) {
                discountPrice += (int) (video.getPrice() * FIVE_YEAR_DISCOUNT_RATE);
                continue;
            }

            if (isPastThreeYear(video.getReleaseDate())) {
                discountPrice += (int) (video.getPrice() * THREE_YEAR_DISCOUNT_RATE);
                continue;
            }

            if (isPastOneYear(video.getReleaseDate())) {
                discountPrice += (int) (video.getPrice() * ONE_YEAR_DISCOUNT_RATE);
                continue;
            }
        }
        return discountPrice;
    }

    private boolean isPastFiveYear(String releaseDate) {
        return isPastYear(releaseDate, 5);
    }

    private boolean isPastThreeYear(String releaseDate) {
        return isPastYear(releaseDate, 3);
    }

    private boolean isPastOneYear(String releaseDate) {
        return isPastYear(releaseDate, 1);
    }

    private boolean isPastYear(String releaseDate, int pastYear) {
        LocalDateTime releaseDateTime = parse(releaseDate);
        return LocalDateTime.now().getYear() - releaseDateTime.getYear() >= pastYear ? true : false;
    }
}
