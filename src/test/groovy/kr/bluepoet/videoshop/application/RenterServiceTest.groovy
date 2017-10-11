package kr.bluepoet.videoshop.application

import kr.bluepoet.videoshop.domain.Rent
import kr.bluepoet.videoshop.domain.RentRepository
import kr.bluepoet.videoshop.domain.RenterRepository
import kr.bluepoet.videoshop.util.DateUtils
import spock.lang.Specification

import static kr.bluepoet.videoshop.util.DateUtils.parse
import static kr.bluepoet.videoshop.util.TestUtils.*

/**
 * Created by bluepoet on 2017. 10. 10..
 */
class RenterServiceTest extends Specification {
    RenterService renterService = Spy()
    RentRepository rentRepository = Mock()
    RenterRepository renterRepository = Mock()

    void setup() {
        renterService.setRentRepository(rentRepository)
        renterService.setRenterRepository(renterRepository)
    }

    def "실버 혹은 골드등급으로 업그레이드 할 자격조건이 되는지 확인한다."() {
        given:
        rentRepository.findByRenter("tester") >> rents
        rentRepository.findByRenter("renter") >> rents
        rentRepository.findByRenter("bluepoet") >> rents
        renterService.currentTime() >> parse('2017-10-06 00:00:00')

        expect:
        renterService.isUpgradeSilver(renter) == firstResult
        renterService.isUpgradeGold(renter) == secondResult

        where:
        rents                   | renter        || firstResult || secondResult
        Collections.emptyList() | NORMAL_RENTER || false       || false
        createTenRents()        | SILVER_RENTER || true        || false
        createTwentyRents()     | GOLD_RENTER   || true        || true
    }

    def "자동탈퇴조건이 되지 않는지 확인한다."() {
        given:
        renterService.currentTime() >> parse('2017-10-06 00:00:00')
        rentRepository.findByRenter("tester") >> Arrays.asList(new Rent(1L, Arrays.asList('video1'), NORMAL_RENTER, DateUtils.nowTime))

        when:
        def result = renterService.isWithdrawCondition(NORMAL_RENTER)

        then:
        result == false
        0 * renterRepository.delete('renter')
    }

    def "가입 후 2년이 지나도록 대여내역이 없어 자동탈퇴조건이 되는지 확인하고, 실제 삭제(DB등에서 삭제)되는지 확인한다."() {
        given:
        renterService.currentTime() >> parse('2017-10-06 00:00:00')
        rentRepository.findByRenter("renter") >> Collections.emptyList()

        when:
        def result = renterService.isWithdrawCondition(SILVER_RENTER)

        then:
        result == true
        1 * renterRepository.delete('renter')
    }

    def createTenRents() {
        createRents(10)
    }

    def createTwentyRents() {
        createRents(20)
    }

    def createRents(repeat) {
        List<Rent> rents = new ArrayList<>()
        (1..repeat).each {
            Rent rent = new Rent(it, Arrays.asList("video" + it), NORMAL_RENTER, DateUtils.nowTime)
            rents.add(rent)
        }
        rents
    }
}