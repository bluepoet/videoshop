package kr.bluepoet.junit5.videoshop.domain;

import kr.bluepoet.videoshop.domain.Video;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VideoTest {
    @Test
    void invalidCreatedVideo() {

        assertInvalidVideo(() -> new Video("terminator", -1000, "2017-10-06 00:00:00"));
        assertInvalidVideo(() -> new Video("", 1000, "2017-10-06 00:00:00"));
        assertInvalidVideo(() -> new Video("alien", 10000, ""));
    }

    private void assertInvalidVideo(Executable executable) {
        assertThrows(IllegalArgumentException.class, executable);
    }
}
