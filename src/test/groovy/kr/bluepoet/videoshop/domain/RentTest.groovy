package kr.bluepoet.videoshop.domain

import kr.bluepoet.videoshop.application.RentService
import spock.lang.Specification

import static kr.bluepoet.videoshop.util.DateUtils.parse
import static kr.bluepoet.videoshop.util.TestUtils.*

/**
 * Created by bluepoet on 2017. 10. 7..
 */
class RentTest extends Specification {
    Rent rent
    RentRepository mockRentRepository
    RentService rentService = Spy()

    void setup() {
        rent = new Rent()
        mockRentRepository = Mock()
        rentService.setRentRepository(mockRentRepository)
    }

    def "대여자 등급별 비디오 3개의 총 대여가격을 계산한다"() {
        given:
        givenMemberDiscountRule(createVideos(), renter, NO_EVENT_TIME)

        expect:
        rent.calculateRentPrice() == totalPrice

        where:
        renter        || totalPrice
        NORMAL_RENTER || 6000
        SILVER_RENTER || 5700
        GOLD_RENTER   || 5400
    }

    def "시간할인이 적용되는 월요일과 주말 시간대 비디오 3개의 총 대여가격을 계산한다"() {
        given:
        givenMemberDiscountRule(createVideos(), NORMAL_RENTER, eventTime)

        expect:
        rent.calculateRentPrice() == totalPrice

        where:
        eventTime          || totalPrice
        NO_EVENT_TIME      || 6000
        MONDAY_EVENT_TIME  || 5820
        WEEKEND_EVENT_TIME || 5700
    }

    def "출시일할인이 적용되는 비디오의 총 대여가격을 계산한다"() {
        given:
        givenMemberDiscountRule(videos, NORMAL_RENTER, NO_EVENT_TIME)

        expect:
        rent.calculateRentPrice() == totalPrice

        where:
        videos                                                                                                                || totalPrice
        Arrays.asList(new Video('alien2', 10000, '2017-10-06 00:00:00'))                                                      || 10000
        Arrays.asList(new Video('alien2', 10000, '2016-10-06 00:00:00'))                                                      || 9700
        Arrays.asList(new Video('terminator', 5000, '2014-10-06 00:00:00'))                                                   || 4750
        Arrays.asList(new Video('predator', 3000, '1986-10-06 00:00:00'))                                                     || 2760
        Arrays.asList(new Video('toy story', 10000, '2016-10-06 00:00:00'), new Video('betman', 3000, '1986-10-06 00:00:00')) || 12460
    }

    def "대여자등급과 시간할인이 중복되었을 때, 비디오 3개의 총 대여가격을 계산한다"() {
        given:
        givenMemberDiscountRule(createVideos(), GOLD_RENTER, WEEKEND_EVENT_TIME)

        when:
        def totalPrice = rent.calculateRentPrice()

        then:
        totalPrice == 5100
    }

    def "반납일에 따라 연체료를 계산한다"() {
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

    def givenMemberDiscountRule(List<Video> videos, Renter renter, String nowTime) {
        rent.addVideo(videos)
        rent.addRenter(renter)
        def totalPrice = getTotalPrice(videos)
        rent.setDiscountRules(Arrays.asList(new MemberDiscountRule(renter.grade, totalPrice), new TimeDiscountRule(nowTime, totalPrice)
                , new ReleaseDateDiscountRule(videos)))
    }

    def getTotalPrice(List<Video> videos) {
        def totalPrice = 0
        videos.each { v ->
            totalPrice += v.getPrice()
        }
        totalPrice
    }
}