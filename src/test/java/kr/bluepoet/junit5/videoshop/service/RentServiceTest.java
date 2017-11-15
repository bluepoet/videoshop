package kr.bluepoet.junit5.videoshop.service;

import kr.bluepoet.junit5.videoshop.util.TestDatas;
import kr.bluepoet.videoshop.application.RentService;
import kr.bluepoet.videoshop.domain.Rent;
import kr.bluepoet.videoshop.domain.RentRepository;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import static kr.bluepoet.videoshop.util.DateUtils.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.doReturn;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RentServiceTest {
    @Spy
    RentService rentService = new RentService();

    @DisplayName("반납일에 따라 연체료를 계산")
    @Test
    void calculatePriceByDelayedDays(@Mock RentRepository rentRepository) {
        // Given
        rentService.setRentRepository(rentRepository);
        given(rentRepository.findById(1L)).willReturn(createRent());
        doReturn(parse("2017-10-18 20:30:33")).when(rentService).currentTime();

        // When
        int delayedPrice = rentService.confirmReturnMoney(1L);

        // Then
        assertEquals(600, delayedPrice);
    }

    private Rent createRent() {
        Rent rent = new Rent();
        rent.addRenter(TestDatas.NORMAL_RENTER);
        rent.addVideo(TestDatas.createVideos());
        rent.setRentDate("2017-10-07 19:30:33");
        return rent;
    }
}
