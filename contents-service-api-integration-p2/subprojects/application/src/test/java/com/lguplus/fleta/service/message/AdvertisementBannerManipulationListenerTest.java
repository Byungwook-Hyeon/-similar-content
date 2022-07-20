package com.lguplus.fleta.service.message;

import com.lguplus.fleta.service.advertisement.AdvertisementBannerDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
class AdvertisementBannerManipulationListenerTest {

    Map<String, String> data;
    Map<String, String> before;

    @Mock(lenient = true)
    WaitableSynchronizedInvoker invoker;

    @Mock
    AdvertisementBannerDomainService advertisementBannerDomainService;

    @Mock
    ImageServerDomainService imageServerDomainService;

    @InjectMocks
    AdvertisementBannerManipulationListener advertisementBannerManipulationListener;

    @BeforeEach
    void setup() {
        data = new HashMap<>();
        data.put("ADS_ID", "AD0001");

        before = new HashMap<>();
        before.put("ADS_ID", "AD0002");

        doAnswer(invocation -> {
            invocation.getArgument(1, Runnable.class).run();
            return null;
        }).when(invoker).invoke(any(), any());
    }

    @Test
    void test_onInsert() {
        assertDoesNotThrow(() -> advertisementBannerManipulationListener.onInsert(data));
    }

    @Test
    void test_onUpdate() {
        assertDoesNotThrow(() -> advertisementBannerManipulationListener.onUpdate(data, data));
    }

    @Test
    void test_onUpdate_Before() {
        assertDoesNotThrow(() -> advertisementBannerManipulationListener.onUpdate(data, before));
    }

    @Test
    void test_onDelete() {
        assertDoesNotThrow(() -> advertisementBannerManipulationListener.onDelete(data));
    }
}
