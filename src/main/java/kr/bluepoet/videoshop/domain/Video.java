package kr.bluepoet.videoshop.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class Video {
    private String name;
    private int price;
    private String releaseDate;

    public Video(String name, int price, String releaseDate) {
        if (isValidParameters(name, price, releaseDate)) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    private boolean isValidParameters(String name, int price, String releaseDate) {
        return StringUtils.isEmpty(name) || isInValidPrice(price) || StringUtils.isEmpty(releaseDate);
    }

    private boolean isInValidPrice(int price) {
        return price < 0;
    }

    public int getPrice() {
        return price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
