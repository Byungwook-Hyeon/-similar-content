package com.lguplus.fleta.service;

import com.lguplus.fleta.data.dto.WatchaCommentDto;
import com.lguplus.fleta.data.dto.request.WatchaCommentRequestDto;
import com.lguplus.fleta.data.dto.response.GenericRecordsetResponseDto;
import com.lguplus.fleta.service.watcha.WatchaCommentDomainService;
import com.lguplus.fleta.util.JunitTestUtils;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WatchaCommentServiceTest {

    @InjectMocks
    WatchaCommentService watchaCommentService;

    @Mock
    private WatchaCommentDomainService watchaCommentDomainService;

    List<WatchaCommentDto> mockResultList;

    @BeforeEach
    void setUp() {
        watchaCommentService = new WatchaCommentService(watchaCommentDomainService);

        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .saId("500175001734")
                .stbMac("8c6d.5044.7e64")
                .albumId("M01203N337PPV00")
                .startNumber(1)
                .requestCount(10)
                .build();

        String albumId = requestDto.getAlbumId();

        mockResultList = new ArrayList<>();
        WatchaCommentDto mockDto = new WatchaCommentDto();
        JunitTestUtils.setValue(mockDto, "commentId", "1");
        JunitTestUtils.setValue(mockDto, "commentWriter", "테스터");
        JunitTestUtils.setValue(mockDto, "commentText", "댓글 내용");
        JunitTestUtils.setValue(mockDto, "commentRating", "5");
        JunitTestUtils.setValue(mockDto, "commentDate", "2021-12-28 14:20:16.000");

        mockResultList.add(mockDto);

        given(watchaCommentDomainService.getWatchaCommentList(requestDto)).willReturn(mockResultList);

        given(watchaCommentDomainService.getWatchaCommentListForPage(mockResultList, requestDto)).willReturn(mockResultList);

    }

    @Test
    @DisplayName("왓챠 댓글 리스트 조회 및 결과리턴 테스트")
    void test_getWatchaCommentList() {

        WatchaCommentRequestDto requestDto = WatchaCommentRequestDto.builder()
                .saId("500175001734")
                .stbMac("8c6d.5044.7e64")
                .albumId("M01093HA81PPV000")
                .startNumber(2)
                .requestCount(10)
                .build();

        GenericRecordsetResponseDto<WatchaCommentDto> result = watchaCommentService.getWatchaCommentList(requestDto);

        assertThat(result.getFlag()).isEqualTo("0000");
    }

}