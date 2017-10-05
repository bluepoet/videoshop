package kr.bluepoet.videoshop.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by bluepoet on 2017. 10. 6..
 */
public class Video {
    private String name;
    private int price;

    public Video(String name, int price) {
        if(StringUtils.isEmpty(name) || isInValidPrice(price)) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
    }

    private boolean isInValidPrice(int price) {
        return price < 0;
    }
}
