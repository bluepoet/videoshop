package kr.bluepoet.videoshop.domain;

import java.util.List;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class Rent {
    private List<Video> videos;
    private Member renter;
    private List<DiscountRule> discountRules;

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

        return applyDiscountRule(totalPrice);
    }

    private int applyDiscountRule(int totalPrice) {
        if (discountRules != null && discountRules.size() != 0) {
            for (DiscountRule discountRule : discountRules) {
                return discountRule.discount(totalPrice);
            }
        }

        return totalPrice;
    }

    public void addRenter(Member p) {
        this.renter = renter;
    }
}
