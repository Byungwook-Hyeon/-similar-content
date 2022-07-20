package com.lguplus.fleta.service.watcha;

import com.lguplus.fleta.client.VodLookupDomainClient;
import com.lguplus.fleta.data.dto.WatchaCommentDto;
import com.lguplus.fleta.data.dto.request.WatchaCommentRequestDto;
import com.lguplus.fleta.exception.UndefinedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class WatchaCommentDomainService {

    private final VodLookupDomainClient vodLookupDomainClient;

    /**
     * Watcha 댓글 리스트 조회 (IMCS 연동 - outer API)
     *
     * @param requestDto 요청 파라미터 DTO
     * @return List<WatchaCommentDto> 결과 DTO List
     */
    public List<WatchaCommentDto> getWatchaCommentList(WatchaCommentRequestDto requestDto) {

        List<WatchaCommentDto> watchaCommentList = vodLookupDomainClient.getWatchaCommentList(requestDto.getAlbumId());

        return watchaCommentList.stream()
                .filter(dto -> Objects.nonNull(dto.getCommentRating()))
                .map(dto -> {
                    String commentRating = dto.getCommentRating();
                    commentRating = commentRating.replaceFirst("0+$", "").replaceFirst("\\.$", "");
                    dto.setCommentRating(commentRating);
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * 페이징 처리
     *
     * @param watchaCommentList 결과 DTO List
     * @param requestDto        요청 파라미터 DTO
     * @return List<WatchaCommentDto> 결과 DTO List
     */
    public List<WatchaCommentDto> getWatchaCommentListForPage(List<WatchaCommentDto> watchaCommentList, WatchaCommentRequestDto requestDto) {

        List<WatchaCommentDto> resultList = null;

        int startNumber = requestDto.getStartNumber();
        int requestCount = requestDto.getRequestCount();
        int endNumber = (startNumber + requestCount) - 1;

        if (startNumber != 0) {
            if (startNumber == -1) {
                startNumber = 1;
                endNumber = watchaCommentList.size();
            } else if (startNumber > watchaCommentList.size()) {
                return Collections.emptyList();
            } else if (endNumber > watchaCommentList.size()) {
                endNumber = watchaCommentList.size();
            }

            try {
                resultList = watchaCommentList.stream()
                        .skip((long) startNumber - 1)
                        .limit(endNumber)
                        .collect(Collectors.toList());
            } catch (Exception ex) {
                throw new UndefinedException();
            }
        }

        return resultList;
    }
}