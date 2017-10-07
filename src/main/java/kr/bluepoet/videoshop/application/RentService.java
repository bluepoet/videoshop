package kr.bluepoet.videoshop.application;

import kr.bluepoet.videoshop.domain.Rent;
import kr.bluepoet.videoshop.domain.RentRepository;
import kr.bluepoet.videoshop.util.DateUtils;

import java.time.LocalDateTime;

/**
 * Created by bluepoet on 2017. 10. 7..
 */
public class RentService {
    private RentRepository rentRepository;

    public void setRentRepository(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public int confirmReturnMoney(long rentId) {
        Rent rent = rentRepository.findById(rentId);
        return rent.calculateDelayMoney(currentTime());
    }

    public LocalDateTime currentTime() {
        return DateUtils.getNowTime();
    }
}
