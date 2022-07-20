package com.lguplus.fleta.client;

import com.lguplus.fleta.data.dto.TestSubscriberDto;

import java.util.List;

public interface SubscriberDomainClient {

    List<TestSubscriberDto> getTestSubscribers();
}
