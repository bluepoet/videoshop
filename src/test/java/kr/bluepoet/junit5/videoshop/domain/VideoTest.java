package kr.bluepoet.junit5.videoshop.domain;

import kr.bluepoet.videoshop.domain.Video;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VideoTest {
    @Test
    void invalidCreatedVideo() {
        assertThrows(IllegalArgumentException.class, () -> new Video("terminator", -1000, "2017-10-06 00:00:00"));
    }
}
