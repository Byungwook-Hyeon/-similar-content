package com.lguplus.fleta.service.caching;

import com.lguplus.fleta.data.annotation.SynchronousScheduled;
import com.lguplus.fleta.data.dto.TestSubscriberDto;
import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.service.subscriber.SubscriberDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Slf4j
public class SubscriberCachingService {

    private final SubscriberDomainService subscriberDomainService;

    @SynchronousScheduled(cron = "0 0/10 * * * *", semaphore = CacheName.TEST_SUBSCRIBER)
    public void cacheTestSubscribers() {

        log.debug("Caching TestSubscribers");

        final Set<TestSubscriberDto> registeredTestSubscribers =
                new HashSet<>(subscriberDomainService.getRegisteredTestSubscribers());
        final List<TestSubscriberDto> testSubscribers = subscriberDomainService.getTestSubscribers();
        testSubscribers.forEach(e -> {
            subscriberDomainService.addTestSubscriber(e);
            registeredTestSubscribers.remove(e);
        });

        registeredTestSubscribers.forEach(subscriberDomainService::removeTestSubscriber);
        subscriberDomainService.registerTestSubscribers(testSubscribers);
    }
}
