package kr.bluepoet.videoshop.application

import kr.bluepoet.videoshop.domain.Rent
import kr.bluepoet.videoshop.domain.RentRepository
import spock.lang.Specification

import static kr.bluepoet.videoshop.util.DateUtils.parse
import static kr.bluepoet.videoshop.util.TestUtils.createVideos
import static kr.bluepoet.videoshop.util.TestUtils.NORMAL_RENTER

/**
 * Created by bluepoet on 2017. 10. 10..
 */
class RentServiceTest extends Specification {
    RentRepository mockRentRepository = Mock()
    RentService rentService = Spy()

    void setup() {
        rentService.setRentRepository(mockRentRepository)
    }

    def "반납일에 따라 연체료를 계산한다."() {
        given:
        def rentId = 1L
        mockRentRepository.findById(rentId) >> givenRentById()
        rentService.currentTime() >> currentTime

        expect:
        rentService.confirmReturnMoney(rentId) == delayMoney

        where:
        currentTime                  || delayMoney
        parse('2017-10-09 19:30:33') || 0
        parse('2017-10-10 20:30:33') || 300
        parse('2017-10-18 20:30:33') || 600
        parse('2017-12-10 20:30:33') || 1800
    }

    def givenRentById() {
        Rent rent = new Rent()
        rent.addRenter(NORMAL_RENTER)
        rent.addVideo(createVideos())
        rent.setRentDate('2017-10-07 19:30:33')
        rent
    }
}