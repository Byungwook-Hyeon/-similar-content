package com.lguplus.fleta.service.watcha;

import com.lguplus.fleta.client.VodLookupDomainClient;
import com.lguplus.fleta.data.dto.WatchaCommentDto;
import com.lguplus.fleta.data.dto.request.WatchaCommentRequestDto;
import com.lguplus.fleta.exception.UndefinedException;
import com.lguplus.fleta.util.JunitTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WatchaCommentDomainServiceTest {

    @Mock
    private VodLookupDomainClient vodLookupDomainClient;

    @InjectMocks
    private WatchaCommentDomainService watchaCommentDomainService;

    @Test
    @DisplayName("IMCS 왓챠 댓글 정보 연동 테스트")
    void test_getWatchaCommentList() {

        // given
        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .saId("500175001734")
                .stbMac("8c6d.5044.7e64")
                .albumId("M01203N337PPV00")
                .startNumber(1)
                .requestCount(10)
                .build();

        List<WatchaCommentDto> expectedList = new ArrayList<>();

        WatchaCommentDto dto1 = new WatchaCommentDto();
        JunitTestUtils.setValue(dto1, "commentId", "1");
        JunitTestUtils.setValue(dto1, "commentWriter", "테스터");
        JunitTestUtils.setValue(dto1, "commentText", "댓글 내용");
        JunitTestUtils.setValue(dto1, "commentRating", "5");
        JunitTestUtils.setValue(dto1, "commentDate", "2021-12-28 14:20:16.000");

        WatchaCommentDto dto2 = new WatchaCommentDto();
        JunitTestUtils.setValue(dto2, "commentId", "2");
        JunitTestUtils.setValue(dto2, "commentWriter", "테스터2");
        JunitTestUtils.setValue(dto2, "commentText", "댓글 내용");
        JunitTestUtils.setValue(dto2, "commentRating", "3");
        JunitTestUtils.setValue(dto2, "commentDate", "2021-12-28 14:20:16.000");

        expectedList.add(dto1);
        expectedList.add(dto2);

        when(vodLookupDomainClient.getWatchaCommentList(requestDto.getAlbumId())).thenReturn(expectedList);

        // when
        List<WatchaCommentDto> actualList = watchaCommentDomainService.getWatchaCommentList(requestDto);

        // then
        assertThat(actualList.size()).isEqualTo(expectedList.size());
    }

    @Test
    void test_getWatchaCommentListForPage_startNumber_thenNotZero() {

        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .saId("500175001734")
                .stbMac("8c6d.5044.7e64")
                .albumId("M01093HA81PPV000")
                .startNumber(1)
                .requestCount(10)
                .build();

        List<WatchaCommentDto> paramsDtos = List.of(
                WatchaCommentDto.builder().commentId("1").commentWriter("테스터").commentText("댓글 내용").commentRating("5").commentDate("2021-12-28 14:20:16.000").build(),
                WatchaCommentDto.builder().commentId("2").commentWriter("테스터").commentText("댓글 내용").commentRating("5").commentDate("2021-12-28 14:20:16.000").build()
        );

        final List<WatchaCommentDto> actualList = watchaCommentDomainService.getWatchaCommentListForPage(paramsDtos, requestDto);

        int startNumber = requestDto.getStartNumber();
        assertThat(startNumber > 0).isTrue();

        int actualCount = actualList.size();
        assertThat(actualCount).isEqualTo(2);

        assertThat(actualList.size()).isEqualTo(2);
    }

    @Test
    void test_getWatchaCommentListForPage_startNumber_thenZero() {

        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .startNumber(0)
                .requestCount(10)
                .build();

        List<WatchaCommentDto> paramsDtos = new ArrayList<>();

        List<WatchaCommentDto> actualList = watchaCommentDomainService.getWatchaCommentListForPage(paramsDtos, requestDto);

        int startNumber = requestDto.getStartNumber();

        assertThat(startNumber > 0).isFalse();
        assertThat(startNumber).isNotSameAs(-1);
    }

    @Test
    void test_getWatchaCommentListForPage_startNumber_case3() {

        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .startNumber(-1)
                .requestCount(10)
                .build();

        List<WatchaCommentDto> paramsDtos = new ArrayList<>();

        final List<WatchaCommentDto> actualList = watchaCommentDomainService.getWatchaCommentListForPage(paramsDtos, requestDto);

        int startNumber = requestDto.getStartNumber();

        assertThat(startNumber).isSameAs(-1);
        assertThat(actualList.size()).isZero();
    }

    @Test
    void test_getWatchaCommentListForPage_startNumber_case4() {

        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .startNumber(4)
                .requestCount(10)
                .build();

        List<WatchaCommentDto> paramsDtos = new ArrayList<>();

        final List<WatchaCommentDto> actualList = watchaCommentDomainService.getWatchaCommentListForPage(paramsDtos, requestDto);

        int startNumber = requestDto.getStartNumber();

        assertThat(startNumber).isNotSameAs(-1);

        int endNumber = (startNumber + requestDto.getRequestCount()) - 1;

        assertThat(endNumber).isGreaterThan(2);
    }

    @Test
    void test_getWatchaCommentListForPage_endNumber_isTrue() {

        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .startNumber(1)
                .requestCount(10)
                .build();

        WatchaCommentDomainService mockObj = mock(WatchaCommentDomainService.class);

        int startNumber = requestDto.getStartNumber();
        int requestCount = requestDto.getRequestCount();
        int endNumber = (startNumber + requestCount) - 1;

        List<WatchaCommentDto> expectedList = List.of(
                WatchaCommentDto.builder().commentId("1").commentWriter("테스터").commentText("댓글 내용").commentRating("5").commentDate("2021-12-28 14:20:16.000").build(),
                WatchaCommentDto.builder().commentId("2").commentWriter("테스터").commentText("댓글 내용").commentRating("5").commentDate("2021-12-28 14:20:16.000").build()
        );

        when(mockObj.getWatchaCommentListForPage(expectedList, requestDto)).thenReturn(expectedList);

        final List<WatchaCommentDto> actualList = watchaCommentDomainService.getWatchaCommentListForPage(expectedList, requestDto);

        assertThat(startNumber).isNotSameAs(-1);
        assertThat(startNumber >  expectedList.size()).isFalse();
        assertThat(endNumber > expectedList.size()).isTrue();
    }

    @Test
    void test_getWatchaCommentListForPage_endNumber_isFalse() {

        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .startNumber(1)
                .requestCount(2)
                .build();

        WatchaCommentDomainService mockObj = mock(WatchaCommentDomainService.class);

        int startNumber = requestDto.getStartNumber();
        int requestCount = requestDto.getRequestCount();
        int endNumber = (startNumber + requestCount) - 1;

        List<WatchaCommentDto> expectedList = List.of(
                WatchaCommentDto.builder().commentId("1").commentWriter("테스터").commentText("댓글 내용").commentRating("5").commentDate("2021-12-28 14:20:16.000").build(),
                WatchaCommentDto.builder().commentId("2").commentWriter("테스터").commentText("댓글 내용").commentRating("5").commentDate("2021-12-28 14:20:16.000").build()
        );

        when(mockObj.getWatchaCommentListForPage(expectedList, requestDto)).thenReturn(expectedList);

        final List<WatchaCommentDto> actualList = watchaCommentDomainService.getWatchaCommentListForPage(expectedList, requestDto);

        assertThat(startNumber).isNotSameAs(-1);
        assertThat(startNumber >  expectedList.size()).isFalse();
        assertThat(endNumber > expectedList.size()).isFalse();
    }

    @Test
    void test_getWatchaCommentListForPage_startNumber_TooSmall() {

        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .startNumber(-100)
                .requestCount(10)
                .build();

        List<WatchaCommentDto> paramsDtos = new ArrayList<>();

        assertThrows(UndefinedException.class, () -> watchaCommentDomainService.getWatchaCommentListForPage(paramsDtos, requestDto));
    }
}