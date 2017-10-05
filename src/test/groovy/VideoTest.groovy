import kr.bluepoet.videoshop.domain.Video
import spock.lang.Specification


/**
 * Created by bluepoet on 2017. 10. 6..
 */
class VideoTest extends Specification {
    Video video

    def "Video를 만들 수 없는 케이스를 확인한다."() {
        when:
        video = new Video('terminator', -1000)

        then:
        thrown IllegalArgumentException

        when:
        video = new Video('', 1000)

        then:
        thrown IllegalArgumentException
    }
}