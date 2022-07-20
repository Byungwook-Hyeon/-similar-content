package com.lguplus.fleta.provider.rest;

import com.lguplus.fleta.data.dto.TestSubscriberDto;
import com.lguplus.fleta.data.dto.response.inner.InnerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "subscriberLongLatency", url = "${service.subscriber.url}")
public interface SubscriberLongLatencyFeignClient {

    @GetMapping(value = "/subscriber/testSubscribers", produces = "application/json")
    InnerResponseDto<List<TestSubscriberDto>> getTestSubscribers();
}
