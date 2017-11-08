package kr.bluepoet.junit5.videoshop.domain;

import kr.bluepoet.videoshop.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static kr.bluepoet.junit5.videoshop.util.TestDatas.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentTest {
    private Rent rent;

    @BeforeEach
    void setUp() {
        rent = new Rent();
    }

    @DisplayName("대여자 등급별 비디오 가격 계산")
    @Test
    void calculatePriceByMemberGrade() {
        // Given
        givenMemberDiscountRule(createVideos(), NORMAL_RENTER, NO_EVENT_TIME);

        // When
        // Then
        assertEquals(6000, rent.calculateRentPrice());

        // Given
        givenMemberDiscountRule(createVideos(), SILVER_RENTER, NO_EVENT_TIME);

        // When
        // Then
        assertEquals(5700, rent.calculateRentPrice());

        // Given
        givenMemberDiscountRule(createVideos(), GOLD_RENTER, NO_EVENT_TIME);

        // When
        // Then
        assertEquals(5400, rent.calculateRentPrice());
    }

    private void givenMemberDiscountRule(List<Video> videos, Renter renter, String nowTime) {
        rent.addVideo(videos);
        rent.addRenter(renter);
        int totalPrice = getTotalPrice(videos);
        rent.setDiscountRules(Arrays.asList(new MemberDiscountRule(renter.getGrade(), totalPrice)
                , new TimeDiscountRule(nowTime, totalPrice)
                , new ReleaseDateDiscountRule(videos)));
    }

    private int getTotalPrice(List<Video> videos) {
        return videos.stream().mapToInt(v -> v.getPrice()).sum();
    }
}
