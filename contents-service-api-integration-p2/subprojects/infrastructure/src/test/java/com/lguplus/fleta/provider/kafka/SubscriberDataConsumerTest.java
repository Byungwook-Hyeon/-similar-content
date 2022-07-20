package com.lguplus.fleta.provider.kafka;

import com.lguplus.fleta.service.DataManipulationListener;
import com.lguplus.fleta.service.ManipulationDataDispatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class SubscriberDataConsumerTest {

    @Mock
    ManipulationDataDispatcher dispatcher;

    @Mock
    DataManipulationListener subscriberManipulationListener;

    @InjectMocks
    SubscriberDataConsumer subscriberDataConsumer;

    @Test
    void testOnSubscriberManipulated() {

        assertDoesNotThrow(() -> subscriberDataConsumer.onSubscriberManipulated("{\"payload\":{\"data\":{}}}"));
    }
}
