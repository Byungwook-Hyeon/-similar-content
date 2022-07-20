package com.lguplus.fleta.service.caching;

import com.lguplus.fleta.data.annotation.SynchronousScheduled;
import com.lguplus.fleta.data.dto.HotVodRecordDto;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class HotVodCachingService {

    private final HotVodDomainService hotVodDomainService;
    private final ImageServerDomainService imageServerDomainService;
    private final SubscriberDomainService subscriberDomainService;

//    @SynchronousScheduled(cron = "30 * * * * *", semaphore = "HOTVODSERVER")
    public void refreshHotVodCache() {
        log.debug("HotVod Caching putNoticePopsCache");

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
    }
}
