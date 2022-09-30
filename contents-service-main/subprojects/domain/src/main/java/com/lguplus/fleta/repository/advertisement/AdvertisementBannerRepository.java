package com.lguplus.fleta.repository.advertisement;

import com.lguplus.fleta.data.dto.AdsLiveInfoDto;

import java.util.List;

public interface AdvertisementBannerRepository {
    List<AdsLiveInfoDto> getIptvAdvertisementBannerList(String advertisementId, String imgResizeServer);
    List<AdsLiveInfoDto> getMobileAdvertisementBannerList(String advertisementId, String imgResizeServer);
}
