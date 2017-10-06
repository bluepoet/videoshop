import kr.bluepoet.videoshop.domain.DiscountRule
import kr.bluepoet.videoshop.domain.Member
import kr.bluepoet.videoshop.domain.MemberDiscountRule
import kr.bluepoet.videoshop.domain.MemberGrade
import kr.bluepoet.videoshop.domain.Rent
import kr.bluepoet.videoshop.domain.Video
import spock.lang.Specification


/**
 * Created by bluepoet on 2017. 10. 6..
 */
class VideoTest extends Specification {
    Video video

    def "Video를 만들 수 없는 케이스를 확인한다."() {
        when:
        video = new Video('terminator', -1000, '2017-10-06 00:00:00')

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

    def "할인되지 않은 비디오 3개의 총 대여가격을 계산한다"() {
        given:
        Video video1 = new Video('alien', 1000, '2017-10-06 00:00:00')
        Video video2 = new Video('terminator', 2000, '2017-10-07 00:00:00')
        Video video3 = new Video('toys', 3000, '2017-10-08 00:00:00')
        List<Video> videos = Arrays.asList(video1, video2, video3)
        Rent rent = new Rent()
        rent.addVideo(videos)

        when:
        def totalPrice = rent.calculateRentPrice()

        then:
        totalPrice == 6000
    }

    def "회원등급이 실버일 때, 회원등급 할인을 적용해 비디오 3개의 총 대여가격을 계산한다"() {
        given:
        Video video1 = new Video('alien', 1000, '2017-10-06 00:00:00')
        Video video2 = new Video('terminator', 2000, '2017-10-07 00:00:00')
        Video video3 = new Video('toys', 3000, '2017-10-08 00:00:00')
        List<Video> videos = Arrays.asList(video1, video2, video3)
        Member member = new Member("bluepoet", MemberGrade.SILVER)
        Rent rent = new Rent()
        rent.addVideo(videos)
        rent.addRenter(member)
        DiscountRule memberDiscountRule = new MemberDiscountRule(MemberGrade.SILVER)
        rent.setDiscountRules(Arrays.asList(memberDiscountRule))

        when:
        def totalPrice = rent.calculateRentPrice()

        then:
        totalPrice == 5700
    }
}