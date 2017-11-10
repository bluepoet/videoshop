package kr.bluepoet.junit5.videoshop.domain;

import kr.bluepoet.videoshop.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

    @DisplayName("일반등급유저 테스트")
    @Nested
    public class GivenNormalRenter {
        @BeforeEach
        void givenNormalRenter() {
            rent.addRenter(NORMAL_RENTER);
            rent.addVideo(createVideos());
        }

        @DisplayName("일반등급유저 비디오 가격 계산")
        @Test
        void calculatePriceByNormalRenter() {
            // Given
            rent.setDiscountRules(Arrays.asList(new MemberDiscountRule(NORMAL_RENTER.getGrade(), getTotalPrice())));

            // When
            int totalPrice = rent.calculateRentPrice();

            // Then
            assertEquals(6000, totalPrice);
        }

        @DisplayName("시간할인이 적용되는 월요일과 주말 시간대 비디오 3개의 총 대여가격을 계산")
        @Test
        void calculatePriceByEventTime() {
            // Given
            rent.setDiscountRules(Arrays.asList(new TimeDiscountRule(MONDAY_EVENT_TIME, getTotalPrice())));

            // When
            // Then
            assertEquals(5820, rent.calculateRentPrice());

            // Given
            rent.setDiscountRules(Arrays.asList(new TimeDiscountRule(WEEKEND_EVENT_TIME, getTotalPrice())));

            // When
            // Then
            assertEquals(5700, rent.calculateRentPrice());
        }

        @DisplayName("출시일할인이 적용되는 비디오의 총 대여가격을 계산")
        @Test
        void calculatePriceByReleaseDate() {
            // Given
            List<Video> videos = Arrays.asList(new Video("alien2", 10000, "2016-10-06 00:00:00"));
            rent.setDiscountRules(Arrays.asList(new ReleaseDateDiscountRule(videos)));
            rent.addVideo(videos);

            // When
            // Then
            assertEquals(9700, rent.calculateRentPrice());

            // Given
            rent.addVideo(Arrays.asList(new Video("terminator", 5000, "2014-10-06 00:00:00")));

            // When
            // Then
            assertEquals(4750, rent.calculateRentPrice());

            // Given
            rent.addVideo(Arrays.asList(new Video("predator", 3000, "1986-10-06 00:00:00")));

            // When
            // Then
            assertEquals(2760, rent.calculateRentPrice());

            // Given
            rent.addVideo(Arrays.asList(new Video("toy story", 10000, "2016-10-06 00:00:00"), new Video("betman", 3000, "1986-10-06 00:00:00")));

            // When
            // Then
            assertEquals(12460, rent.calculateRentPrice());
        }
    }
}
