package com.lguplus.fleta.service;

import com.lguplus.fleta.data.dto.WatchaCommentDto;
import com.lguplus.fleta.data.dto.request.WatchaCommentRequestDto;
import com.lguplus.fleta.data.dto.response.GenericRecordsetResponseDto;
import com.lguplus.fleta.service.watcha.WatchaCommentDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class WatchaCommentService {

    private final WatchaCommentDomainService watchaCommentDomainService;

    /**
     * Watcha 댓글 리스트 조회
     *
     * @param requestDto 요청 파라미터 DTO
     * @return GenericRecordsetResponseDto<WatchaCommentDto> 응답 결과 Dto
     */
    public GenericRecordsetResponseDto<WatchaCommentDto> getWatchaCommentList(WatchaCommentRequestDto requestDto) {

        // 1. Watcha 댓글 리스트 조회 (Inner API 호출 - VodLookup 도메인 서비스)
        List<WatchaCommentDto> watchaCommentList = watchaCommentDomainService.getWatchaCommentList(requestDto);

        // 2. 페이징 처리
        List<WatchaCommentDto> resultList = watchaCommentDomainService.getWatchaCommentListForPage(watchaCommentList, requestDto);

        return GenericRecordsetResponseDto.<WatchaCommentDto>genericRecordsetResponseBuilder()
                .totalCount(String.valueOf(watchaCommentList.size()))
                .recordset(resultList)
                .build();
    }
}