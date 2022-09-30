package com.lguplus.fleta.provider.rest;

import com.lguplus.fleta.client.SubscriberDomainClient;
import com.lguplus.fleta.data.dto.TestSubscriberDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SubscriberDomainFeignClient extends CommonDomainFeignClient implements SubscriberDomainClient {

    private final SubscriberLongLatencyFeignClient subscriberLongLatencyFeignClient;

    @Override
    public List<TestSubscriberDto> getTestSubscribers() {

        return getResult(subscriberLongLatencyFeignClient.getTestSubscribers());
    }
}
