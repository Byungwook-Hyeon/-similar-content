package com.lguplus.fleta.service.message;

import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.service.DataManipulationListener;
import com.lguplus.fleta.service.caching.SubscriberCachingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component("subscriberManipulationListener")
@AllArgsConstructor
@Transactional(readOnly = true)
public class SubscriberManipulationListener implements DataManipulationListener {

    private final WaitableSynchronizedInvoker invoker;
    private final SubscriberCachingService subscriberCachingService;

    @Override
    public void onInsert(final Map<String, String> data) {

        if (isTestSubscriber(data)) {
            updateTestSubscriberCache();
        }
    }

    @Override
    public void onUpdate(final Map<String, String> data, final Map<String, String> before) {

        if (isTestSubscriber(data) || isTestSubscriber(before)) {
            updateTestSubscriberCache();
        }
    }

    @Override
    public void onDelete(final Map<String, String> before) {

        if (isTestSubscriber(before)) {
            updateTestSubscriberCache();
        }
    }

    private boolean isTestSubscriber(final Map<String, String> data) {

        return "1".equals(extractValue(data, "PVS_ST_TYP")) && "Y".equals(extractValue(data, "PVS_TEST_SBC"));
    }

    private void updateTestSubscriberCache() {

        invoker.invoke(CacheName.TEST_SUBSCRIBER, subscriberCachingService::cacheTestSubscribers);
    }
}
