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
class AdvertisementBannerDataConsumerTest {

    @Mock
    ManipulationDataDispatcher dispatcher;

    @Mock
    DataManipulationListener advertisementBannerManipulationListener;

    @InjectMocks
    AdvertisementBannerDataConsumer advertisementBannerDataConsumer;

    @Test
    void test_onAdvertisementMasterManipulated() {
        assertDoesNotThrow(() -> advertisementBannerDataConsumer.onAdvertisementMasterIptvManipulated("{\"payload\":{\"data\":{}}}"));
    }

    @Test
    void test_onAdvertisementInfoManipulated() {
        assertDoesNotThrow(() -> advertisementBannerDataConsumer.onAdvertisementInfoIptvManipulated("{\"payload\":{\"data\":{}}}"));
    }
}
