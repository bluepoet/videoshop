package kr.bluepoet.videoshop.application;

import kr.bluepoet.videoshop.domain.Video;
import kr.bluepoet.videoshop.domain.VideoDataSource;

import java.util.Collections;
import java.util.List;

/**
 * Created by bluepoet on 2017. 10. 10..
 */
public class NewVideoService {
    public static final String ADMIN_ID = "bluepoet";
    private VideoDataSource source;

    public void setSource(VideoDataSource source) {
        this.source = source;
    }

    public List<Video> getNewVideoList(String id, String searchDate) {
        if (isAdminId(id)) {
            return source.get(searchDate);
        }

        return Collections.EMPTY_LIST;
    }

    private boolean isAdminId(String id) {
        if (ADMIN_ID.equals(id)) {
            return true;
        }
        return false;
    }
}


