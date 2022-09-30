package com.lguplus.fleta.service.message;

import com.lguplus.fleta.service.caching.SubscriberCachingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class SubscriberManipulationListenerTest {

    @Mock
    WaitableSynchronizedInvoker invoker;

    @Mock
    SubscriberCachingService subscriberCachingService;

    @InjectMocks
    SubscriberManipulationListener subscriberManipulationListener;

    @Test
    void testOnInsertTestSubscriber() {

        final Map<String, String> data = new HashMap<>();
        data.put("PVS_SA_ID", "SA_ID_1");
        data.put("PVS_MAC_ADDR", "MAC_ADDR_1");
        data.put("PVS_ST_TYP", "1");
        data.put("PVS_TEST_SBC", "Y");

        assertDoesNotThrow(() -> subscriberManipulationListener.onInsert(data));
    }

    @Test
    void testOnInsertNormalSubscriberNotSatisfyStTyp() {

        final Map<String, String> data = new HashMap<>();
        data.put("PVS_SA_ID", "SA_ID_1");
        data.put("PVS_MAC_ADDR", "MAC_ADDR_1");
        data.put("PVS_TEST_SBC", "Y");

        assertDoesNotThrow(() -> subscriberManipulationListener.onInsert(data));
    }

    @Test
    void testOnInsertNormalSubscriberNotSatisfyTestSbc() {

        final Map<String, String> data = new HashMap<>();
        data.put("PVS_SA_ID", "SA_ID_1");
        data.put("PVS_MAC_ADDR", "MAC_ADDR_1");
        data.put("PVS_ST_TYP", "1");

        assertDoesNotThrow(() -> subscriberManipulationListener.onInsert(data));
    }

    @Test
    void testOnInsertNormalSubscriberNotSatisfyStTypAndTestSbc() {

        final Map<String, String> data = new HashMap<>();
        data.put("PVS_SA_ID", "SA_ID_1");
        data.put("PVS_MAC_ADDR", "MAC_ADDR_1");

        assertDoesNotThrow(() -> subscriberManipulationListener.onInsert(data));
    }

    @Test
    void testOnUpdateTestSubscribeToNormalSubscriber() {

        final Map<String, String> before = new HashMap<>();
        before.put("PVS_SA_ID", "SA_ID_1");
        before.put("PVS_MAC_ADDR", "MAC_ADDR_1");
        before.put("PVS_ST_TYP", "1");
        before.put("PVS_TEST_SBC", "Y");

        final Map<String, String> data = new HashMap<>();
        data.put("PVS_SA_ID", "SA_ID_1");
        data.put("PVS_MAC_ADDR", "MAC_ADDR_1");

        assertDoesNotThrow(() -> subscriberManipulationListener.onUpdate(before, data));
    }

    @Test
    void testOnUpdateNormalSubscriberToTestSubscriber() {

        final Map<String, String> before = new HashMap<>();
        before.put("PVS_SA_ID", "SA_ID_1");
        before.put("PVS_MAC_ADDR", "MAC_ADDR_1");

        final Map<String, String> data = new HashMap<>();
        data.put("PVS_SA_ID", "SA_ID_1");
        data.put("PVS_MAC_ADDR", "MAC_ADDR_1");
        data.put("PVS_ST_TYP", "1");
        data.put("PVS_TEST_SBC", "Y");

        assertDoesNotThrow(() -> subscriberManipulationListener.onUpdate(before, data));
    }

    @Test
    void testOnUpdateNormalSubscriberToNormalSubscriber() {

        final Map<String, String> before = new HashMap<>();
        before.put("PVS_SA_ID", "SA_ID_1");
        before.put("PVS_MAC_ADDR", "MAC_ADDR_1");

        final Map<String, String> data = new HashMap<>();
        data.put("PVS_SA_ID", "SA_ID_1");
        data.put("PVS_MAC_ADDR", "MAC_ADDR_1");

        assertDoesNotThrow(() -> subscriberManipulationListener.onUpdate(before, data));
    }

    @Test
    void testOnDeleteTestSubscriber() {

        final Map<String, String> data = new HashMap<>();
        data.put("PVS_SA_ID", "SA_ID_1");
        data.put("PVS_MAC_ADDR", "MAC_ADDR_1");
        data.put("PVS_ST_TYP", "1");
        data.put("PVS_TEST_SBC", "Y");

        assertDoesNotThrow(() -> subscriberManipulationListener.onDelete(data));
    }

    @Test
    void testOnDeleteNormalSubscriber() {

        final Map<String, String> data = new HashMap<>();
        data.put("PVS_SA_ID", "SA_ID_1");
        data.put("PVS_MAC_ADDR", "MAC_ADDR_1");

        assertDoesNotThrow(() -> subscriberManipulationListener.onDelete(data));
    }
}
