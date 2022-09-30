package com.lguplus.fleta.service.caching;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.service.advertisement.AdvertisementBannerDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;

@ExtendWith(SpringExtension.class)
class AdvertisementBannerCachingServiceTest {
    
    @Mock
    AdvertisementBannerDomainService advertisementBannerDomainService;
    @Mock
    ImageServerDomainService imageServerDomainService;

    @InjectMocks
    AdvertisementBannerCachingService advertisementBannerCachingService;

    @BeforeEach
    void setUp() throws Exception {
        given(imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE)).willReturn("imageServerUrl");
        
        List<AdsLiveInfoDto> allAdvertisementBanners = new ArrayList<>();
        AdsLiveInfoDto entity = AdsLiveInfoDto.builder()
                .advertisementId("AD_ID")
                .advertisementNo("1")
                .title("I don't know!")
                .build();
        allAdvertisementBanners.add(entity);
        given(advertisementBannerDomainService.buildAdvertisementBannerList(any(), anyString())).willReturn(allAdvertisementBanners);
        
        List<AdsLiveInfoDto> resultList = Collections.emptyList();
        given(advertisementBannerDomainService.loadAdvertisementBannerList(anyString(), anyList())).willReturn(resultList);
    }

    @Test
    void testCacheAllAdvertisementBannerList() {
        assertDoesNotThrow(advertisementBannerCachingService::cacheAllAdvertisementBannerList);
    }
}
