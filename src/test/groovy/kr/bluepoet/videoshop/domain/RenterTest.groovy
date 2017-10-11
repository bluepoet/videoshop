package kr.bluepoet.videoshop.domain

import spock.lang.Specification

import static kr.bluepoet.videoshop.util.DateUtils.parse
import static kr.bluepoet.videoshop.util.TestUtils.GOLD_RENTER
import static kr.bluepoet.videoshop.util.TestUtils.SILVER_RENTER

/**
 * Created by bluepoet on 2017. 10. 8..
 */
class RenterTest extends Specification {

    def "첫 가입 후 3년 혹은 5년이 지났는지 확인한다."() {
        given:
        Renter renter = testRenter

        expect:
        renter.isPastThreeYearsFromJoinDate(parse('2017-10-06 00:00:00')) == firstResult
        renter.isPastFiveYearsFromJoinDate(parse('2017-10-06 00:00:00')) == secondResult

        where:
        testRenter    || firstResult || secondResult
        SILVER_RENTER || true        || false
        GOLD_RENTER   || true        || true
    }
}