package kr.bluepoet.junit5.videoshop.service;

import kr.bluepoet.videoshop.application.NewVideoService;
import kr.bluepoet.videoshop.domain.VideoDataSource;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static kr.bluepoet.junit5.videoshop.util.TestDatas.createVideos;
import static kr.bluepoet.videoshop.application.NewVideoService.ADMIN_ID;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class NewVideoServiceTest {
    private static final String NOT_ADMIN_ID = "tester";
    NewVideoService service = new NewVideoService();

    @DisplayName("관리자 아이디가 아닐 때, 신규비디오 리스트 조회권한여부 확인")
    @Test
    public void getNewVideosByNoAdminId(@Mock VideoDataSource source) {
        // Given
        givenVideoDataSource(source);

        // When
        service.getNewVideoList(NOT_ADMIN_ID, "201709");

        // Then
        verify(source, times(0)).get("201709");
    }

    @DisplayName("관리자 아이디 일 때, 신규비디오 리스트 조회권한여부 확인")
    @Test
    public void getNewVideosByAdminId(@Mock VideoDataSource source) {
        // Given
        givenVideoDataSource(source);

        // When
        service.getNewVideoList(ADMIN_ID, "201709");

        // Then
        verify(source, times(1)).get("201709");
    }

    @ParameterizedTest
    @ValueSource(strings = {NewVideoService.ADMIN_ID})
    public void confirmAdminId(String userId) {
        assertTrue(NewVideoService.ADMIN_ID.equals(userId));
    }

    private void givenVideoDataSource(@Mock VideoDataSource source) {
        given(source.get("201709")).willReturn(createVideos());
        service.setSource(source);
    }
}
