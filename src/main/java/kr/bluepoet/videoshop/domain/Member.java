package kr.bluepoet.videoshop.domain;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class Member {
    private String name;
    private MemberGrade grade;

    public Member(String name, MemberGrade grade) {
        this.name = name;
        this.grade = grade;
    }
}
