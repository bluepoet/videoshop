package kr.bluepoet.videoshop.application

import kr.bluepoet.videoshop.domain.Video
import kr.bluepoet.videoshop.domain.VideoDataSource
import spock.lang.Specification

import static kr.bluepoet.videoshop.application.NewVideoService.ADMIN_ID

/**
 * Created by bluepoet on 2017. 10. 10..
 */
class NewVideoServiceTest extends Specification {
    static String NOT_ADMIN_ID = "tester"
    NewVideoService service = new NewVideoService()
    VideoDataSource source = Mock()

    void setup() {
        service.setSource(source)
    }

    def "조회기간별로 가져오는 신규비디오 리스트를 확인한다."() {
        given:
        source.get("201709") >> createVideos()
        source.get("201710") >> Collections.emptyList()

        when:
        service.getNewVideoList(ADMIN_ID, searchDate) == videos

        then:
        1 * source.get(_)

        where:
        userId   | searchDate || videos
        ADMIN_ID | "201709"   || createVideos()
        ADMIN_ID | "201710"   || Collections.emptyList()
    }

    def "조회 아이디별로 가져오는 신규비디오 리스트를 확인한다."() {
        given:
        source.get("201709") >> createVideos()

        and: "관리자 아이디가 아닌 아이디로 조회할 때"
        when:
        def videos = service.getNewVideoList(NOT_ADMIN_ID, "201709")

        then:
        0 * source.get("201709")
        videos == Collections.emptyList()

        and: "관리자 아이디로 조회할 때"
        when:
        service.getNewVideoList(ADMIN_ID, "201709")

        then:
        1 * source.get("201709")
    }

    def createVideos() {
        Arrays.asList(new Video("Mars", 30000, "2017-11-06 00:00:00")
                , new Video("Iron man", 15000, "2017-12-25 00:00:00")
                , new Video("아저씨", 20000, "2018-12-06 00:00:00"))
    }
}