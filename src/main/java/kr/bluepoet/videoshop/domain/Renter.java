package kr.bluepoet.videoshop.domain;

import java.time.LocalDateTime;

import static kr.bluepoet.videoshop.util.DateUtils.parse;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class Renter {
    private String name;
    private RenterGrade grade;
    private LocalDateTime joinDate;

    public Renter(String name, RenterGrade grade, String joinDate) {
        this.name = name;
        this.grade = grade;
        this.joinDate = parse(joinDate);
    }

    public RenterGrade getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public boolean isPastThreeYearsFromJoinDate(LocalDateTime nowTime) {
        return isPastYears(nowTime, joinDate, 3);
    }


    public boolean isPastFiveYearsFromJoinDate(LocalDateTime nowTime) {
        return isPastYears(nowTime, joinDate, 5);
    }

    public boolean isPastTwoYearsFromJoinDate(LocalDateTime nowTime) {
        return isPastYears(nowTime, joinDate, 2);
    }

    private boolean isPastYears(LocalDateTime currentTime, LocalDateTime joinDateTime, int pastYear) {
        return (currentTime.getYear() - joinDateTime.getYear()) >= pastYear;
    }
}
