package com.lguplus.fleta.service.advertisement;

import com.lguplus.fleta.client.VodLookupDomainClient;
import com.lguplus.fleta.data.dto.AlbumTrailerDto;
import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.exception.ResultNotFoundException;
import com.lguplus.fleta.repository.advertisement.AdvertisementBannerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class AdvertisementBannerDomainService {

    private final AdvertisementBannerRepository advertisementBannerRepository;
    private final VodLookupDomainClient vodLookupDomainClient;

    private static final DateTimeFormatter DATE_TIME_FOMRAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Cacheable(cacheNames = CacheName.ADVERTISEMENT2, key ="#advertisementId")
    public List<AdsLiveInfoDto> getAdvertisementBannerList(String advertisementId, String imageServerUrl) {
        log.debug("getAdvertisementBannerList() - {}:advertisementId={}", "광고 배너 리스트 조회(Cacheable) Key", advertisementId);
        return this.buildAdvertisementBannerList(advertisementId, imageServerUrl);
    }

    public List<AdsLiveInfoDto> buildAdvertisementBannerList(String advertisementId, String imageServerUrl) {
        //먼저 DB에서 리스트 조회
        List<AdsLiveInfoDto> advertisementBanners = advertisementBannerRepository.getIptvAdvertisementBannerList(advertisementId, imageServerUrl);

        //VodLookup 도메인에 albumId 리스트로 trailer 정보 조회(Inner API 호출)
        List<String> albumIds = advertisementBanners.stream()//album id들만 추출해서
                .map(AdsLiveInfoDto::getAlbumId)
                .distinct()
                .collect(Collectors.toList());
        List<AlbumTrailerDto> trailerInfos = vodLookupDomainClient.getTrailerInfo(albumIds);
        Map<String, AlbumTrailerDto> trailerInfoMap = trailerInfos
                .stream()
                .collect(Collectors.toMap(AlbumTrailerDto::getAlbumId, Function.identity()));

        //VodLookup 도메인에서 받은 trailer 정보들을 advertisementBannerList에 채우고(합치고)
        return advertisementBanners.stream()
                .map(adsLiveInfo -> {
                    AlbumTrailerDto trailerInfo = trailerInfoMap.get(adsLiveInfo.getAlbumId());
                    if (trailerInfo != null) {
                        adsLiveInfo.setTrailerSh(trailerInfo.getTrailerLowFile());
                        adsLiveInfo.setTrailerHd(trailerInfo.getTrailerMainFile());
                        adsLiveInfo.setBadge(trailerInfo.getBadgeIcon());
                    }
                    return adsLiveInfo;
                }).collect(Collectors.toList());
    }


    @CachePut(cacheNames=CacheName.ADVERTISEMENT2, key="#advertisementId")
    public List<AdsLiveInfoDto> loadAdvertisementBannerList(String advertisementId, List<AdsLiveInfoDto> advertisementBanners) {
        log.debug("getAdvertisementBannerList() - {}:advertisementId={}", "광고 배너 리스트 조회 및 Cache에 저장 Key", advertisementId);
        return advertisementBanners.stream()
                .filter(ab -> advertisementId.equals(ab.getAdvertisementId()))
                .collect(Collectors.toList());
    }


    public List<AdsLiveInfoDto> getLiveAdvertisementList(List<AdsLiveInfoDto> advertisementBanners) {
        List<AdsLiveInfoDto> results = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        List<AdsLiveInfoDto> filterdAdvertisementBanners = advertisementBanners.stream()
                .filter(ab -> this.isCurrentDateBetweenStartAndExpiredDate(ab, now)) //날짜(년월일시분초)로 필터링 
                .collect(Collectors.toList());

        int order = 0;
        for(AdsLiveInfoDto ab : filterdAdvertisementBanners) {
            String dateType = ab.getDateType();

            if(StringUtils.isBlank(dateType) || "00".equals(dateType)) {
                ab.setOrder(String.valueOf(++order));
                results.add(ab);
            } else {
                //날짜를 모두 같은 날로 바꿔서 시간만 비교
                LocalDateTime changedStartDate = this.changeHourMinute(ab.getStartTime(), now);
                LocalDateTime changedExpiredDate = this.changeHourMinute(ab.getExpiredTime(), now);

                boolean isIncluded = now.isAfter(changedStartDate) && now.isBefore(changedExpiredDate);
                if(isIncluded) {
                    //시간 포맷은 59분이기 때문에 -1분 해서 59으로 변경한 뒤 데이터 입력 -> 무슨 말인지?(그냥 AS-IS와 동일하게 함)
                    ab.setExpiredTime(DATE_TIME_FOMRAT.format(changedExpiredDate.minusMinutes(1L)));
                    ab.setOrder(String.valueOf(++order));
                    results.add(ab);
                }
            }//if
        }//for

        return results;
    }

    private boolean isCurrentDateBetweenStartAndExpiredDate(AdsLiveInfoDto advertisementBanner, LocalDateTime now) {
        LocalDateTime startDate = LocalDateTime.parse(advertisementBanner.getStartTime(), DATE_TIME_FOMRAT);
        LocalDateTime expiredDate = LocalDateTime.parse(advertisementBanner.getExpiredTime(), DATE_TIME_FOMRAT);
        //59분 포맷을 +1분 해서 00으로 변경한 뒤 비교(호환성) -> 무슨 말인지?(그냥 AS-IS와 동일하게 함)  
        expiredDate = expiredDate.plusMinutes(1L);

        return now.isAfter(startDate) && now.isBefore(expiredDate);
    }

    private LocalDateTime changeHourMinute(String date, LocalDateTime now) {
        LocalDateTime dateTime = LocalDateTime.parse(date, DATE_TIME_FOMRAT);
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();

        return now.withHour(hour).withMinute(minute).withSecond(0);
    }


    /**
     * 시작 인덱스가 전체 크기보다 큰 경우 -> 범위오류(데이터 없음)
     *  - flag.range6 5010
     *  - message.range6 데이터 미존재
     */
    public List<AdsLiveInfoDto> getPaginatedAdvertisementBannerList(List<AdsLiveInfoDto> liveAdvertisements, int totalCount, int startNumber, int requestCount) {
        if(startNumber == 0) {
            return Collections.emptyList();
        }

        if(startNumber > totalCount || startNumber < -totalCount) { //시작 인덱스가 전체 크기보다 큰 경우 -> 범위오류(데이터 없음)
            throw new ResultNotFoundException(); //5010:데이터 미존재
        }

        int shiftNumber = (startNumber > 0) ? (totalCount - startNumber + 1) : -startNumber;
        Collections.rotate(liveAdvertisements, shiftNumber);
        return liveAdvertisements.stream().limit(requestCount).collect(Collectors.toList());
    }
}
