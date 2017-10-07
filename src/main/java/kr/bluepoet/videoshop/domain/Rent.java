package kr.bluepoet.videoshop.domain;

import java.util.List;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class Rent {
    private List<Video> videos;
    private Renter renter;
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

        if (discountRules != null && discountRules.size() != 0) {
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

    public void addRenter(Renter p) {
        this.renter = renter;
    }
}
