package kr.bluepoet.videoshop.domain;

import java.util.List;

/**
 * Created by bluepoet on 2017. 10. 10..
 */
public interface VideoDataSource {
    List<Video> get(String searchDate);
}
