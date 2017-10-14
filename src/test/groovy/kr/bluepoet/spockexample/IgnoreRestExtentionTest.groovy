package kr.bluepoet.spockexample

import spock.lang.IgnoreRest
import spock.lang.Specification


/**
 * Created by bluepoet on 2017. 10. 14..
 */
class IgnoreRestExtentionTest extends Specification {
    @IgnoreRest
    def "이 feature 메서드만 실행된다."() {
        expect:
        1 == 1
    }

    def "이 feature 메서드는 무시된다."() {
        expect:
        1 == 2
    }

    def "이 feature 메서드도 무시된다."() {
        expect:
        1 == 3
    }
}