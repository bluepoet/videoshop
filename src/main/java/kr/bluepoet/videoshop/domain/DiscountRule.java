package kr.bluepoet.videoshop.domain;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public interface DiscountRule {
    int discount(int totalPrice);
}
