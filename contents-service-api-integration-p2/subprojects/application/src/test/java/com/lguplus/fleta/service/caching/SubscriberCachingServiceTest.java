package com.lguplus.fleta.service.caching;

import com.lguplus.fleta.data.dto.TestSubscriberDto;
import com.lguplus.fleta.service.subscriber.SubscriberDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class SubscriberCachingServiceTest {

    @Mock
    private SubscriberDomainService subscriberDomainService;

    @InjectMocks
    private SubscriberCachingService subscriberCachingService;

    @BeforeEach
    void setUp() {

        final TestSubscriberDto testSubscriber1 = new TestSubscriberDto();
        testSubscriber1.setSaId("SA_ID_1");
        testSubscriber1.setMacAddr("MAC_ADDR_1");
        final TestSubscriberDto testSubscriber2 = new TestSubscriberDto();
        testSubscriber2.setSaId("SA_ID_2");
        testSubscriber2.setMacAddr("MAC_ADDR_2");

        doReturn(List.of(testSubscriber1)).when(subscriberDomainService).getTestSubscribers();
        doReturn(List.of(testSubscriber1, testSubscriber2)).when(subscriberDomainService)
                .getRegisteredTestSubscribers();
    }

    @Test
    void testCacheTestSubscribers() {

        assertDoesNotThrow(subscriberCachingService::cacheTestSubscribers);
    }
}
