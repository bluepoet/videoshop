package kr.bluepoet.videoshop.domain

import spock.lang.Specification

/**
 * Created by bluepoet on 2017. 10. 6..
 */
class VideoTest extends Specification {
    def "Video를 만들 수 없는 케이스를 확인한다."() {
        when:
        Video video = new Video('terminator', -1000, '2017-10-06 00:00:00')

        then:
        thrown IllegalArgumentException

        when:
        video = new Video('', 1000, '2017-10-06 00:00:00')

        then:
        thrown IllegalArgumentException

        when:
        video = new Video('alien', 10000, '')

        then:
        thrown IllegalArgumentException
    }

    def "Video를 만들 수 없는 케이스를 확인한다.(Data Driven Testing)"() {
        when:
        Video video = new Video(name, price, releaseDate)

        then:
        thrown IllegalArgumentException

        where:
        name         | price | releaseDate
        'terminator' | -1000 | '2017-10-06 00:00:00'
        ''           | 1000  | '2017-10-06 00:00:00'
        'alien'      | 10000 | ''
    }
}