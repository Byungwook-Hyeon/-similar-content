package com.lguplus.fleta.domain.repository;

import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import com.lguplus.fleta.provider.jpa.AdvertisementBannerJpaEmRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AdvertisementBannerRepositoryImplTest {

    @Mock
    AdvertisementBannerJpaEmRepository advertisementBannerJpaEmRepository;

    @InjectMocks
    AdvertisementBannerRepositoryImpl advertisementBannerRepository;

    @Test
    void testGetIptvAdvertisementLiveMeta() {

        doReturn(List.of()).when(advertisementBannerJpaEmRepository).getIptvAdvertisementBannerList(any(), any());

        final List<AdsLiveInfoDto> result = advertisementBannerRepository.getIptvAdvertisementBannerList(
                "1", "imgResizeServer");
        assertThat(result).isEmpty();
    }

    @Test
    void testGetMobileAdvertisementLiveMeta() {

        doReturn(List.of()).when(advertisementBannerJpaEmRepository).getMobileAdvertisementBannerList(any(), any());

        final List<AdsLiveInfoDto> result = advertisementBannerRepository.getMobileAdvertisementBannerList(
                "1", "imgResizeServer");
        assertThat(result).isEmpty();
    }
}
