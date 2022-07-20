package com.lguplus.fleta.service.message;

import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.service.DataManipulationListener;
import com.lguplus.fleta.service.advertisement.AdvertisementBannerDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Component("advertisementBannerManipulationListener")
@AllArgsConstructor
@Transactional(readOnly = true)
public class AdvertisementBannerManipulationListener implements DataManipulationListener {

    private final WaitableSynchronizedInvoker invoker;
    private final ImageServerDomainService imageServerDomainService;
    private final AdvertisementBannerDomainService advertisementBannerDomainService;

    @Override
    public void onInsert(final Map<String, String> data) {

        cacheAdvertisementBanner(data);
    }

    @Override
    public void onUpdate(final Map<String, String> data, final Map<String, String> before) {

        cacheAdvertisementBanner(data);
        if (!getAdvertisementId(data).equals(getAdvertisementId(before))) {
            cacheAdvertisementBanner(before);
        }
    }

    @Override
    public void onDelete(final Map<String, String> before) {

        cacheAdvertisementBanner(before);
    }

    private String getAdvertisementId(final Map<String, String> data) {

        return extractValue(data, "ADS_ID");
    }

    private void cacheAdvertisementBanner(final Map<String, String> data) {

        final String advertisementId = getAdvertisementId(data);
        final String imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE);
        invoker.invoke(CacheName.ADVERTISEMENT2 + "::" + advertisementId, () -> {
            final List<AdsLiveInfoDto> banners = advertisementBannerDomainService.buildAdvertisementBannerList(advertisementId, imageServerUrl);
            advertisementBannerDomainService.loadAdvertisementBannerList(advertisementId, banners);
        });
    }
}
