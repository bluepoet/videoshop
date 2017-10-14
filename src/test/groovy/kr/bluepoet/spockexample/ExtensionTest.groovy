package kr.bluepoet.spockexample

import kr.bluepoet.videoshop.domain.Video
import spock.lang.FailsWith
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Timeout

/**
 * Created by bluepoet on 2017. 10. 14..
 */
class ExtensionTest extends Specification {
    @Timeout(1)
    def "timeout 설정을 테스트한다"() {
        expect:
        Thread.sleep(500)
    }

    @Ignore
    def "이 feature method는 동작하지 않는다."() {
        expect:
        1 == 2
    }

    def "thrown으로 던져진 exception을 검증한다."() {
        when:
        new Video('', 1000, '2017-10-14 00:00:00')

        then:
        thrown(IllegalArgumentException.class)
    }

    @FailsWith(IllegalArgumentException)
    def "@FailsWith 어노테이션으로 던져진 exception을 검증한다."() {
        expect:
        new Video('alien', 1000, '')
    }
}