package com.lguplus.fleta.service;

import com.lguplus.fleta.data.dto.AlbumTrailerDto;
import com.lguplus.fleta.data.dto.HotVodRecordDto;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import com.lguplus.fleta.data.dto.response.GenericHotVodResponseDto;
import com.lguplus.fleta.data.dto.response.SuccessResponseDto;
import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.service.hotvod.HotVodDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import com.lguplus.fleta.service.subscriber.SubscriberDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class HotVodService {

    private final HotVodDomainService hotVodDomainService;
    private final ImageServerDomainService imageServerDomainService; //NOTE: Programming 도메인의 ImageServerDomainService.java 소스와 동일
    private final SubscriberDomainService subscriberDomainService;

    public SuccessResponseDto addHotVodHitCount(String contentId) {
        log.debug("addHotVodHitCount() - {}:contentId={}", "화제 동영상 조회 수 저장", contentId);

        //Cache에서 contentId에 대한 hit count를 가져온다.(Caching된 hit count 정보가 없으면 자동으로 Cache에 hit count가 0로 추가된다.)
        int count = hotVodDomainService.getHotVodHitCount(contentId);
        //Cache에서 가져온 hit count에 1을 더해서 다시 Cache에 넣어준다.
        hotVodDomainService.setHotVodHitCount(contentId, count + 1);

        return SuccessResponseDto.builder().build();
    }

    /**
     * 화재 동영상 목록
     */
    public GenericHotVodResponseDto<HotVodRecordDto> getNewHotVodList(HotVodListRequestDto request) {
        String imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE); //get 이미지 resize server url
        request.setImageServerUrl(imageServerUrl.concat("iptv/hotvod/"));
        log.debug("getNewHatVodList() - {}:request={}", "화재 동영상 목록", ToStringBuilder.reflectionToString(request, ToStringStyle.JSON_STYLE));

        //Cacheable 기본 데이터 가져오기
        List<HotVodRecordDto> hotVodRecords = new ArrayList<>();
        if(StringUtils.isNotBlank(request.getParentCate())) {
            Arrays.stream(request.getParentCate().split("\\|"))
                    .forEach(pc -> {
                        request.setParentCate(pc);
                        log.debug("getParentCate() - {}:request={}", pc, ToStringBuilder.reflectionToString(request, ToStringStyle.JSON_STYLE));
                        hotVodRecords.addAll(hotVodDomainService.getHotVodLoadData(request));
                    });
        } else {
            hotVodRecords.addAll(hotVodDomainService.getHotVodLoadData(request));
        }

        //getFilteringSiteList
        List<String> filteringSites = new ArrayList<>();
        Map<String, List<String>>  filteringSiteMap = hotVodDomainService.getFilteringSiteList();
        String filteringId = request.getFilteringId();
        if(filteringSiteMap.get(filteringId) != null) {
            filteringSites.addAll(filteringSiteMap.get(filteringId));
        }
        request.setFilteringSite(filteringSites);

        //getHotvodSearchList
        List<HotVodRecordDto> newHotVodRecords = hotVodDomainService.getHotVodSearchList(request, hotVodRecords);

        //hitmap get/set cachable에서 가져온다.
        newHotVodRecords
                .forEach(
                        newHotVod -> {
                            if(StringUtils.isNotBlank(newHotVod.getContentId())) {
                                int hitCount = hotVodDomainService.getHotVodHitCount(newHotVod.getContentId());
                                int curHit = hitCount + Integer.parseInt(newHotVod.getHitCnt());
                                newHotVod.setHitCnt(Integer.toString(curHit));
                            }
                        }
                );

        //검수단말기 필터
        boolean testSubscriber = subscriberDomainService.isTestSubscriber(request.getSaId(), request.getStbMac()); //key:MacAddr, value:SaId
        newHotVodRecords = hotVodDomainService.getFilteredHotvodList(newHotVodRecords, testSubscriber);

        //노출기간 체크
        newHotVodRecords = hotVodDomainService.getHotVodOrderList(newHotVodRecords);

        //Order 적용
        newHotVodRecords = hotVodDomainService.getHotVodRecordComparator(request, newHotVodRecords);

        // start_num 과 req_count에 맞게 데이터 갯수 다시 셋팅
        Integer totalCount = newHotVodRecords.size();
        newHotVodRecords = hotVodDomainService.getHotVodsLimt(request, newHotVodRecords);

        //MetaData cachable M Ttype 에 대해서 대상을 조회하고 등록한다.
        List<AlbumTrailerDto> albumTrailers = new ArrayList<>();
        newHotVodRecords.stream()//album id들만 추출해서
                .filter(x->StringUtils.equals("M",x.getType()))
                .map(HotVodRecordDto::getVodAlbumId)
                .distinct()
                .forEach(
                        albumId -> albumTrailers.addAll(hotVodDomainService.getMetadataList(albumId))
                );

        //MetaData Set
        newHotVodRecords = hotVodDomainService.setMetadataList(newHotVodRecords, albumTrailers);

        return GenericHotVodResponseDto.<HotVodRecordDto>genericHotVodResponseBuilder()
                .version(request.getVersion())
                .totalCnt(String.valueOf(totalCount))
                .filteringSite(filteringSites)
                .recordset(newHotVodRecords)
                .build();
    }


    /**
     * BaseNotice 정보 refreshCache
     */
    public GenericHotVodResponseDto<HotVodRecordDto> refreshHotVodCache() {

        String serverUrlDist = imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE);

        HotVodListRequestDto request = HotVodListRequestDto
                .builder()
                .parentCate("")
                .type("C|V|M|N|L")
                .imageServerUrl(serverUrlDist.concat("iptv/hotvod/"))
                .build();

        //Cacheable 기본 데이터 가져오기
        List<HotVodRecordDto> hotVodAllRecords = hotVodDomainService.putHotVodLoadData(request);
        Map<String, List<HotVodRecordDto>> newHotVodParentCateMap = hotVodAllRecords.stream()
                .filter(x-> StringUtils.isNotBlank(x.getParentCate()))
                .collect(Collectors.groupingBy(HotVodRecordDto::getParentCate));
        List<String> keyList = new ArrayList<>(newHotVodParentCateMap.keySet());
        keyList
                .forEach(pc -> {
                    request.setParentCate(pc);
                    log.debug("getParentCate() - {}:request={}", pc, ToStringBuilder.reflectionToString(request, ToStringStyle.JSON_STYLE));
                    hotVodDomainService.putHotVodLoadData(request);
                });

        List<String> filteringSite = new ArrayList<>();
        hotVodDomainService.loadFilteringSiteList();

        //검수단말기 필터
        boolean testSubscriber = subscriberDomainService.isTestSubscriber(request.getSaId(), request.getStbMac()); //key:MacAddr, value:SaId
        hotVodAllRecords = hotVodDomainService.getFilteredHotvodList(hotVodAllRecords, testSubscriber);

        hotVodAllRecords.stream()//album id들만 추출해서
                .filter(x->StringUtils.equals("M",x.getType()))
                .map(HotVodRecordDto::getVodAlbumId)
                .distinct()
                .forEach(
                        hotVodDomainService::putMetadataList
                );

        return GenericHotVodResponseDto.<HotVodRecordDto>genericHotVodResponseBuilder()
                .recordset(hotVodAllRecords)
                .filteringSite(filteringSite)
                .build();
    }

}
