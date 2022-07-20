package com.lguplus.fleta.domain.repository;

import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import com.lguplus.fleta.provider.jpa.AdvertisementBannerJpaEmRepository;
import com.lguplus.fleta.repository.advertisement.AdvertisementBannerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AdvertisementBannerRepositoryImpl implements AdvertisementBannerRepository {

    private final AdvertisementBannerJpaEmRepository advertisementBannerJpaEmRepository;

    @Override
    public List<AdsLiveInfoDto> getIptvAdvertisementBannerList(String advertisementId, String imgResizeServer) {
        return advertisementBannerJpaEmRepository.getIptvAdvertisementBannerList(advertisementId, imgResizeServer);
    }

    @Override
    public List<AdsLiveInfoDto> getMobileAdvertisementBannerList(String advertisementId, String imgResizeServer) {
        return advertisementBannerJpaEmRepository.getMobileAdvertisementBannerList(advertisementId, imgResizeServer);
    }
}
