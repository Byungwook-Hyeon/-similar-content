package com.lguplus.fleta.service;

import com.lguplus.fleta.data.dto.AdvertisementBannerDto;
import com.lguplus.fleta.data.dto.response.GenericRecordsetResponseDto;
import com.lguplus.fleta.data.dto.response.SuccessResponseDto;
import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.service.advertisement.AdvertisementBannerDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AdvertisementBannerService {

    private final AdvertisementBannerDomainService advertisementBannerDomainService;
    private final ImageServerDomainService imageServerDomainService; //NOTE: Programming 도메인의 ImageServerDomainService.java 소스와 동일 

    public GenericRecordsetResponseDto<AdvertisementBannerDto> getAdvertisementBannerList(String advertisementId, Integer startNumber, Integer requestCount) {
        log.debug("getAdvertisementBannerList() - {}:advertisementId={}", "광고 배너 리스트 조회", advertisementId);

        String imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE); //get 이미지 resize server url
        List<AdsLiveInfoDto> advertisementBanners = advertisementBannerDomainService.getAdvertisementBannerList(advertisementId, imageServerUrl);

        // time filtering
        List<AdsLiveInfoDto> liveAdvertisements = advertisementBannerDomainService.getLiveAdvertisementList(advertisementBanners);
        int totalCount = liveAdvertisements.size();

        // startNumber가 있으면 Paging 적용
        if(startNumber != null) {
            liveAdvertisements = advertisementBannerDomainService.getPaginatedAdvertisementBannerList(liveAdvertisements, totalCount, startNumber, requestCount);
        }

        List<AdvertisementBannerDto> recordSet = this.convertRecordSet(liveAdvertisements);
        return GenericRecordsetResponseDto.<AdvertisementBannerDto>genericRecordsetResponseBuilder()
                .totalCount(Integer.toString(totalCount) )
                .recordset(recordSet)
                .build();
    }

    private List<AdvertisementBannerDto> convertRecordSet(List<AdsLiveInfoDto> liveAdvertisements) {
        return liveAdvertisements.stream().map(e ->
                AdvertisementBannerDto.builder()
                        .advertisementId(e.getAdvertisementId())
                        .startTime(e.getStartTime())
                        .dateType(e.getDateType())
                        .advertisementNo(e.getAdvertisementNo())
                        .title(e.getTitle())
                        .imageUrl(e.getSaveFileName())
                        .advertisementType(e.getAdvertisementType())
                        .duration(e.getDuration())
                        .advertisementUrl(e.getAdvertisementUrl())
                        .expiredTime(e.getExpiredTime())
                        .grade(e.getGrade())
                        .eventId(e.getEventId())
                        .order(e.getOrder())
                        .backgroundColor(e.getBackgroundColor())
                        .verticalBackgroundUrl(e.getBackgroundSaveFileNameVertical())
                        .horizontalBackgroundUrl(e.getBackgroundSaveFileNameHorizontal())
                        .imageUrl2(e.getSaveFileName2())
                        .etc(e.getEtc())
                        .etc2(e.getEtc2())
                        .trailerSh(e.getTrailerSh())
                        .trailerHd(e.getTrailerHd())
                        .badge(e.getBadge())
                        .build()
        ).collect(Collectors.toList());
    }



    /**
     * NoticePop 정보 refreshCache
     */
    public SuccessResponseDto refreshAdvertisementBannerCache() {
        String imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE); //get 이미지 resize server url
        List<AdsLiveInfoDto> allAdvertisementBanners = advertisementBannerDomainService.buildAdvertisementBannerList(null, imageServerUrl);

        log.debug("Caching All AdvertisementBanners");
        allAdvertisementBanners.stream()
                .map(AdsLiveInfoDto::getAdvertisementId)
                //.distinct()
                .forEach(advertisementId -> advertisementBannerDomainService.loadAdvertisementBannerList(advertisementId, allAdvertisementBanners));

        return SuccessResponseDto.builder().build();
    }

}
