package com.lguplus.fleta.service;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import com.lguplus.fleta.service.advertisement.AdvertisementMetaDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
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
class AdvertisementMetaServiceTest {

    @Mock
    AdvertisementMetaDomainService advertisementMetaDomainService;

    @Mock
    ImageServerDomainService imageServerDomainService;

    @InjectMocks
    AdvertisementMetaService advertisementMetaService;

    @Test
    void testGetAdvertisementLiveMetaIptv() {

        doReturn(List.of(new AdvertisementMetaDto())).when(advertisementMetaDomainService).getAdvertisementLiveMeta(any());
        doReturn("http://localhost/smartux/").when(imageServerDomainService).getImageServerUrl(any());

        final List<AdvertisementMetaDto> result = advertisementMetaService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().build());
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getImageServerUrl()).isEqualTo("http://localhost/smartux/ads/");
    }

    @Test
    void testGetAdvertisementLiveMetaMobile() {

        doReturn(List.of(new AdvertisementMetaDto())).when(advertisementMetaDomainService).getAdvertisementLiveMeta(any());
        doReturn("http://localhost/smartux/").when(imageServerDomainService).getImageServerUrl(any());

        final List<AdvertisementMetaDto> result = advertisementMetaService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().screenType("N").build());
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getImageServerUrl()).isEqualTo("http://localhost/smartux/hdtv/img/");
    }
}
