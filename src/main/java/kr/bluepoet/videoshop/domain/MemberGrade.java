package kr.bluepoet.videoshop.domain;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public enum MemberGrade {
    NORMAL(0.0F),
    SILVER(0.05F),
    GOLD(0.1F);

    private float discountRate;

    MemberGrade(float discountRate) {
        this.discountRate = discountRate;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public boolean isDiscountMember() {
        if (this == SILVER || this == GOLD) {
            return true;
        }
        return false;
    }
}
