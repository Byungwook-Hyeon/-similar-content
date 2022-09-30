package com.lguplus.fleta.service.subscriber;

import com.lguplus.fleta.client.SubscriberDomainClient;
import com.lguplus.fleta.data.dto.TestSubscriberDto;
import com.lguplus.fleta.data.type.CacheName;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubscriberDomainService {

    private final SubscriberDomainClient subscriberDomainClient;

    @Cacheable(value = CacheName.TEST_SUBSCRIBER, key = "#saId + '.' + #stbMac", unless = "!#result")
    public boolean isTestSubscriber(final String saId, final String stbMac) {

        return false;
    }

    @CachePut(value = CacheName.TEST_SUBSCRIBER, key = "#testSubscriber.saId + '.' + #testSubscriber.macAddr")
    public boolean addTestSubscriber(final TestSubscriberDto testSubscriber) {

        return true;
    }

    @CacheEvict(value = CacheName.TEST_SUBSCRIBER, key = "#testSubscriber.saId + '.' + #testSubscriber.macAddr")
    public void removeTestSubscriber(final TestSubscriberDto testSubscriber) {

        // Do nothing.
    }

    public List<TestSubscriberDto> getTestSubscribers() {

        return subscriberDomainClient.getTestSubscribers();
    }

    @Cacheable(value = CacheName.TEST_SUBSCRIBER_LIST, key = "'DEFAULT'")
    public List<TestSubscriberDto> getRegisteredTestSubscribers() {

        return List.of();
    }

    @CachePut(value = CacheName.TEST_SUBSCRIBER_LIST, key = "'DEFAULT'")
    public List<TestSubscriberDto> registerTestSubscribers(final List<TestSubscriberDto> testSubscribers) {

        return testSubscribers;
    }
}
