package com.lguplus.fleta.service.caching;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("!local")
//@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CacheInitializationService {

    private final SchedulingTaskExecutor schedulingTaskExecutor;

    private final ImageServerCachingService imageServerCachingService;
    private final AdvertisementBannerCachingService advertisementBannerCachingService;
    private final SubscriberCachingService subscriberCachingService;
    private final HotVodCachingService hotVodCachingService;


    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        schedulingTaskExecutor.submit(imageServerCachingService::cacheImageServers);
        schedulingTaskExecutor.submit(advertisementBannerCachingService::cacheAllAdvertisementBannerList);
        schedulingTaskExecutor.submit(subscriberCachingService::cacheTestSubscribers);
        /**
         * TODO: VodLookup 연동 완료 후 재등록해야 함
         */
//        schedulingTaskExecutor.submit(hotVodCachingService::refreshHotVodCache);
    }
}
