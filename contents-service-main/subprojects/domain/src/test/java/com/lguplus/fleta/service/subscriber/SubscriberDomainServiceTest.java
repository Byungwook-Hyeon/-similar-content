package com.lguplus.fleta.service.subscriber;

import com.lguplus.fleta.client.SubscriberDomainClient;
import com.lguplus.fleta.data.dto.TestSubscriberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class SubscriberDomainServiceTest {

    @Mock
    SubscriberDomainClient subscriberDomainClient;

    @InjectMocks
    SubscriberDomainService subscriberDomainService;

    @BeforeEach
    void setUp() {

        final TestSubscriberDto testSubscriber = new TestSubscriberDto();
        testSubscriber.setSaId("SA_ID_1");
        testSubscriber.setMacAddr("MAC_ADDR_1");
        doReturn(List.of(testSubscriber)).when(subscriberDomainClient).getTestSubscribers();
    }

    @Test
    void testIsTestSubscriber() {

        final boolean testSubscriber = subscriberDomainService.isTestSubscriber(null, null);
        assertThat(testSubscriber).isFalse();
    }

    @Test
    void testAddTestSubscriber() {

        final boolean testSubscriber1 = subscriberDomainService.addTestSubscriber(null);
        assertThat(testSubscriber1).isTrue();
    }

    @Test
    void testRemoveTestSubscriber() {

        assertDoesNotThrow(() -> subscriberDomainService.removeTestSubscriber(null));
    }

    @Test
    void testGetTestSubscribers() {

        final List<TestSubscriberDto> testSubscribers = subscriberDomainService.getTestSubscribers();
        assertThat(testSubscribers).hasSize(1);

        final TestSubscriberDto testSubscriber = testSubscribers.get(0);
        assertThat(testSubscriber.getSaId()).isEqualTo("SA_ID_1");
        assertThat(testSubscriber.getMacAddr()).isEqualTo("MAC_ADDR_1");
    }

    @Test
    void testGetRegisteredTestSubscribers() {

        final List<TestSubscriberDto> testSubscribers = subscriberDomainService.getRegisteredTestSubscribers();
        assertThat(testSubscribers).isEmpty();
    }

    @Test
    void testRegisterTestSubscribers() {

        final List<TestSubscriberDto> testSubscribers = subscriberDomainService.registerTestSubscribers(
                subscriberDomainService.getTestSubscribers());
        assertThat(testSubscribers).hasSize(1);

        final TestSubscriberDto testSubscriber = testSubscribers.get(0);
        assertThat(testSubscriber.getSaId()).isEqualTo("SA_ID_1");
        assertThat(testSubscriber.getMacAddr()).isEqualTo("MAC_ADDR_1");
    }
}
