package kr.bluepoet.videoshop.domain;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class MemberDiscountRule implements DiscountRule {
    private MemberGrade memberGrade;

    public MemberDiscountRule(MemberGrade memberGrade) {
        this.memberGrade = memberGrade;
    }

    @Override
    public int discount(int totalPrice) {
        if (memberGrade.isDiscountMember()) {
            return (int) (totalPrice - (totalPrice * memberGrade.getDiscountRate()));
        }

        return totalPrice;
    }
}
