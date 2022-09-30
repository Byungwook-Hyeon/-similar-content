package com.lguplus.fleta.service.caching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

@ExtendWith(SpringExtension.class)
class CacheInitializationServiceTest {

    @Mock
    ImageServerCachingService imageServerCachingService;
    @Mock
    AdvertisementBannerCachingService advertisementBannerCachingService;
    @Mock
    HotVodCachingService hotVodCachingService;
    @Mock
    SubscriberCachingService subscriberCachingService;

    @BeforeEach
    void setUp() throws Exception {
        doNothing().when(advertisementBannerCachingService).cacheAllAdvertisementBannerList();
    }

    @Test
    void testInitialize() {
        SchedulingTaskExecutor schedulingTaskExecutor = spy(new ConcurrentTaskExecutor());
        doCallRealMethod().when(schedulingTaskExecutor).submit((Runnable) any());

        CacheInitializationService cacheInitializationService = new CacheInitializationService(
            schedulingTaskExecutor
            , imageServerCachingService
            , advertisementBannerCachingService
            , subscriberCachingService
            , hotVodCachingService
        );
        assertDoesNotThrow(cacheInitializationService::initialize);
    }

}
