package com.lguplus.fleta.service.caching;

import com.lguplus.fleta.data.annotation.SynchronousScheduled;
import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.service.advertisement.AdvertisementBannerDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AdvertisementBannerCachingService {

    private AdvertisementBannerDomainService advertisementBannerDomainService;
    private ImageServerDomainService imageServerDomainService;

    @SynchronousScheduled(cron = "0 0/10 * * * *", semaphore =CacheName.ADVERTISEMENT2)
    public void cacheAllAdvertisementBannerList() {
        String imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE); //get 이미지 resize server url
        List<AdsLiveInfoDto> allAdvertisementBanners = advertisementBannerDomainService.buildAdvertisementBannerList(null, imageServerUrl);
        this.cacheAdvertisementBannerListByAdvertisementId(allAdvertisementBanners);
    }

    public void cacheAdvertisementBannerListByAdvertisementId(List<AdsLiveInfoDto> allAdvertisementBanners) {
        log.debug("Caching All AdvertisementBanners");

        allAdvertisementBanners.stream()
                .map(AdsLiveInfoDto::getAdvertisementId)
                //.distinct()
                .forEach(advertisementId -> advertisementBannerDomainService.loadAdvertisementBannerList(advertisementId, allAdvertisementBanners));
    }
}
