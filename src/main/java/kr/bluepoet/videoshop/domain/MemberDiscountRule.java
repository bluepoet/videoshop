package kr.bluepoet.videoshop.domain;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class MemberDiscountRule implements DiscountRule {
    private RenterGrade memberGrade;
    private int totalPrice;

    public MemberDiscountRule(RenterGrade memberGrade, int totalPrice) {
        this.memberGrade = memberGrade;
        this.totalPrice = totalPrice;
    }

    @Override
    public int discount() {
        if (memberGrade.isDiscountMember()) {
            return (int) (totalPrice * memberGrade.getDiscountRate());
        }

        return 0;
    }
}
