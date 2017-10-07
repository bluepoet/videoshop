package kr.bluepoet.videoshop.util

import kr.bluepoet.videoshop.domain.Renter
import kr.bluepoet.videoshop.domain.RenterGrade
import kr.bluepoet.videoshop.domain.Video

/**
 * Created by bluepoet on 2017. 10. 7..
 */
class TestUtils {
    static def NORMAL_RENTER = new Renter("tester", RenterGrade.NORMAL, '2017-10-06 00:00:00')
    static def SILVER_RENTER = new Renter("renter", RenterGrade.SILVER, '2014-10-06 00:00:00')
    static def GOLD_RENTER = new Renter("bluepoet", RenterGrade.GOLD, '2012-10-06 00:00:00')

    static def NO_EVENT_TIME = '2017-10-11 10:30:33'
    static def MONDAY_EVENT_TIME = '2017-10-09 10:30:33'
    static def WEEKEND_EVENT_TIME = '2017-10-14 18:30:33'

    static def createVideos() {
        Video video1 = new Video('alien', 1000, '2017-10-06 00:00:00')
        Video video2 = new Video('terminator', 2000, '2017-10-07 00:00:00')
        Video video3 = new Video('toys', 3000, '2017-10-08 00:00:00')
        Arrays.asList(video1, video2, video3)
    }
}
