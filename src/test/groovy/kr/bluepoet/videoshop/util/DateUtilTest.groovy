package kr.bluepoet.videoshop.util

import spock.lang.Specification

import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * Created by bluepoet on 2017. 10. 7..
 */
class DateUtilTest extends Specification {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    def "월요일 10~11시 사이의 시간이 맞는지 확인한다."() {
        given:
        LocalDateTime nowTime = LocalDateTime.parse("2017-10-09 10:30:33", formatter);
        LocalDateTime endTime = LocalDateTime.parse("2017-10-09 11:00:00", formatter);

        when:
        DayOfWeek dayOfWeek = nowTime.getDayOfWeek()
        Duration duration = Duration.between(nowTime, endTime)

        then:
        dayOfWeek == DayOfWeek.MONDAY
        duration.getSeconds() == 1767
    }
}