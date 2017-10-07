package kr.bluepoet.videoshop.domain;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class Renter {
    private String name;
    private RenterGrade grade;

    public Renter(String name, RenterGrade grade) {
        this.name = name;
        this.grade = grade;
    }

    public RenterGrade getGrade() {
        return grade;
    }
}
