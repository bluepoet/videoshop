package kr.bluepoet.videoshop.application;

import kr.bluepoet.videoshop.domain.Rent;
import kr.bluepoet.videoshop.domain.RentRepository;
import kr.bluepoet.videoshop.domain.Renter;
import kr.bluepoet.videoshop.domain.RenterRepository;
import kr.bluepoet.videoshop.util.DateUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bluepoet on 2017. 10. 8..
 */
public class RenterService {
    private RentRepository rentRepository;
    private RenterRepository renterRepository;

    public void setRentRepository(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public void setRenterRepository(RenterRepository renterRepository) {
        this.renterRepository = renterRepository;
    }

    public boolean isUpgradeSilver(Renter renter) {
        List<Rent> rents = rentRepository.findByRenter(renter.getName());
        if (isSilverGradeCondition(renter, rents)) {
            return true;
        }
        return false;
    }

    private boolean isSilverGradeCondition(Renter renter, List<Rent> rents) {
        return rents.size() >= 10 && renter.isPastThreeYearsFromJoinDate(currentTime());
    }

    public boolean isUpgradeGold(Renter renter) {
        List<Rent> rents = rentRepository.findByRenter(renter.getName());
        if (isGoldGradeCondition(renter, rents)) {
            return true;
        }

        return false;
    }

    private boolean isGoldGradeCondition(Renter renter, List<Rent> rents) {
        return rents.size() >= 20 && renter.isPastFiveYearsFromJoinDate(currentTime());
    }

    public boolean isWithdrawCondition(Renter renter) {
        List<Rent> rents = rentRepository.findByRenter(renter.getName());
        if (matchWithdrawCondition(renter, rents)) {
            renterRepository.delete(renter.getName());
            return true;
        }

        return false;
    }

    private boolean matchWithdrawCondition(Renter renter, List<Rent> rents) {
        return CollectionUtils.isEmpty(rents) && renter.isPastTwoYearsFromJoinDate(currentTime());
    }

    public LocalDateTime currentTime() {
        return DateUtils.getNowTime();
    }
}
