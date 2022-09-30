package com.lguplus.fleta.provider.rest;

import com.lguplus.fleta.data.dto.TestSubscriberDto;
import com.lguplus.fleta.data.dto.response.inner.InnerResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class SubscriberDomainFeignClientTest {

    @Mock
    private SubscriberLongLatencyFeignClient subscriberLongLatencyFeignClient;

    @InjectMocks
    private SubscriberDomainFeignClient subscriberDomainFeignClient;

    @BeforeEach
    void setup() {

        final TestSubscriberDto testSubscriber = new TestSubscriberDto();
        testSubscriber.setSaId("SA_ID_1");
        testSubscriber.setMacAddr("MAC_ADDR_1");
        doReturn(InnerResponseDto.of(List.of(testSubscriber))).when(subscriberLongLatencyFeignClient).getTestSubscribers();
    }

    @Test
    void testGetTestSubscribers() {

        final List<TestSubscriberDto> testSubscribers = subscriberDomainFeignClient.getTestSubscribers();
        assertThat(testSubscribers).hasSize(1);

        final TestSubscriberDto testSubscriber = testSubscribers.get(0);
        assertThat(testSubscriber.getSaId()).isEqualTo("SA_ID_1");
        assertThat(testSubscriber.getMacAddr()).isEqualTo("MAC_ADDR_1");
    }
}
